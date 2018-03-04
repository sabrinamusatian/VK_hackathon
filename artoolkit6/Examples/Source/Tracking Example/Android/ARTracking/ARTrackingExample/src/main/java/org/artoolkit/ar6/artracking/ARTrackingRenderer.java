package org.artoolkit.ar6.artracking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLES20;

import org.artoolkit.ar6.base.ARActivity;
import org.artoolkit.ar6.base.ARToolKit;
import org.artoolkit.ar6.base.NativeInterface;
import org.artoolkit.ar6.base.rendering.ARRenderer;
import org.artoolkit.ar6.base.rendering.shader_impl.Cube;
import org.artoolkit.ar6.base.rendering.shader_impl.SimpleFragmentShader;
import org.artoolkit.ar6.base.rendering.shader_impl.SimpleShaderProgram;
import org.artoolkit.ar6.base.rendering.shader_impl.SimpleVertexShader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * A very simple Renderer that adds a marker and draws a cube on it.
 */
class ARTrackingRenderer extends ARRenderer {

    Activity mContext;
    ARTrackingRenderer(Activity context){
        super();
        mContext = context;
    }
    private static final class Trackable {
        String name;
        float height;
        Trackable(String name, float height)
        {
            this.name = name;
            this.height = height;
        }
    }
    private static final Trackable trackables[] = new Trackable[]{
        new Trackable("Alterra_Ticket_1.jpg", 95.3f),
        new Trackable("Alterra_Postcard_2.jpg", 95.3f),
        new Trackable("Alterra_Postcard_3.jpg", 127.0f),
        new Trackable("Alterra_Postcard_4.jpg", 95.3f),
        new Trackable("Rembrandt.jpg", 95.3f)
    };
    private int trackableUIDs[] = new int[trackables.length];
    
    private Cat cube;
    private RightArrow rArrow;
    /**
     * Markers can be configured here.
     */
    @Override
    public boolean configureARScene() {
        int i = 0;
        for (Trackable trackable : trackables) {
            trackableUIDs[i] = ARToolKit.getInstance().addMarker("2d;Data/2d/" + trackable.name + ";" + trackable.height);
            if (trackableUIDs[i] < 0) return false;
            i++;
        }
        NativeInterface.arwSetTrackerOptionInt(NativeInterface.ARW_TRACKER_OPTION_2D_MAX_IMAGES, trackables.length);
        return true;
    }

    //Shader calls should be within a GL thread. GL threads are onSurfaceChanged(), onSurfaceCreated() or onDrawFrame()
    //As the cube instantiates the shader during setShaderProgram call we need to create the cube here.
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        this.shaderProgram = new SimpleShaderProgram(new SimpleVertexShader(), new SimpleFragmentShader());
        cube = new Cat(15.0f, 30.0f, -30.0f, 0.0f);
        cube.setShaderProgram(shaderProgram);
        rArrow = new RightArrow(5.0f, 30.0f, -30.0f, 0.0f);
        rArrow.setShaderProgram(shaderProgram);
        super.onSurfaceCreated(unused, config);
    }

    /**
     * Override the draw function from ARRenderer.
     */
    @Override
    public void draw() {
        super.draw();

        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glFrontFace(GLES20.GL_CCW);

        if (ARToolKit.getInstance().queryMarkerVisible(trackableUIDs[4])) {
            float[] projectionMatrix = ARToolKit.getInstance().getProjectionMatrix();
            float[] modelViewMatrix = ARToolKit.getInstance().queryMarkerTransformation(trackableUIDs[4]);
            if (cube.it > 80) {
                rArrow.draw(projectionMatrix, modelViewMatrix);
            } else {
                cube.draw(projectionMatrix, modelViewMatrix);
            }
        } else if (ARToolKit.getInstance().queryMarkerVisible(trackableUIDs[3])) {
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mContext, map.class);
                    intent.putExtra("hall", 2);  // TODO
                    mContext.startActivity(intent);
                }
            });

        }
    }
}