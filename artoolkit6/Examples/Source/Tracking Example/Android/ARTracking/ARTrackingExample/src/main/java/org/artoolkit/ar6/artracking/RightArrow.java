package org.artoolkit.ar6.artracking;

/**
 * Created by sabrina on 21.10.2017.
 */

import org.artoolkit.ar6.base.rendering.ARDrawable;
import org.artoolkit.ar6.base.rendering.ShaderProgram;
import org.artoolkit.ar6.base.rendering.util.RenderUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.artoolkit.ar6.base.rendering.ARDrawable;
import org.artoolkit.ar6.base.rendering.util.RenderUtils;
import org.artoolkit.ar6.base.rendering.ShaderProgram;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class RightArrow implements ARDrawable{

    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;
    private ShaderProgram shaderProgram;
    private int it = 0;
    private float mx, my, mz, msize;
    @SuppressWarnings("unused")
    public RightArrow() {
        this(1.0f);
    }

    @SuppressWarnings("WeakerAccess")
    public RightArrow(float size) {
        this(size, 0.0f, 0.0f, 0.0f);
    }

    public RightArrow(float size, float x, float y, float z) {
        mx = x;
        my = y;
        mz = z;
        msize = size;
        setArrays(size, x, y, z);
    }

    @SuppressWarnings("unused")
    public RightArrow(ShaderProgram shaderProgram) {
        super();
        this.shaderProgram = shaderProgram;
    }

    @SuppressWarnings("WeakerAccess")
    public FloatBuffer getmVertexBuffer() {
        return mVertexBuffer;
    }
    @SuppressWarnings("WeakerAccess")
    public FloatBuffer getmColorBuffer() {
        return mColorBuffer;
    }
    @SuppressWarnings("WeakerAccess")
    public ByteBuffer getmIndexBuffer() {
        return mIndexBuffer;
    }

    private void setArrays(float size, float x, float y, float z) {

        float hs = size / 2.0f;
        float headsize = size / 2.0f;
        float vertices[] = {
                // body
                x - hs, y - hs, z - hs, // 0 --> back bottom left
                x + hs, y - hs, z - hs, // 1 --> back bottom right
                x + hs, y - hs, z + hs, // 2 --> front right bottom
                x - hs, y - hs, z + hs, // 3 --> front left bottom
                x - hs, y + hs, z - hs, // 4 --> back top left
                x + hs, y + hs, z - hs, // 5 --> back right top
                x + hs, y + hs, z + hs, // 6 --> front right top
                x - hs, y + hs, z + hs, // 7 --> front left top
                // head
                x + hs, y - hs - headsize, z - hs - headsize, // 8 --> back bottom right
                x + hs, y - hs - headsize, z + hs + headsize, // 9 --> front right bottom
                x + hs, y + hs + headsize, z - hs - headsize, // 10 --> back right top
                x + hs, y + hs + headsize, z + hs + headsize, // 11 --> front right top
                x + hs + 2*headsize, y, z, // 12 --> pointing
        };
        float c = 0.7f;
        float colors[] = {
                c, c, c, c, // 0
                c, c, c, c, // 1
                c, c, c, c, // 2
                c, c, c, c, // 3
                c, c, c, c, // 4
                c, c, c, c, // 5
                c, c, c, c, // 6
                c, c, c, c, // 7
                c, c, c, c, // 8
                c, c, c, c, // 9
                c, c, c, c, // 10
                c, c, c, c, // 11
                c, c, c, c, // 12
        };

        byte indices[] = {
                // body
                // bottom
                2, 0, 1,
                3, 0, 2,
                // top
                5, 4, 6,
                6, 4, 7,
                //back
                0, 4, 1,
                1, 5, 4,
                //left
                0, 3, 7,
                4, 0, 7,
                //right
                1, 5, 2,
                5, 6, 2,
                // front
                2, 6, 7,
                2, 7, 3,

                // head
                // borders
                9, 11, 8,
                8, 10, 11,
                // front
                9, 12, 11,
                // back
                8, 12, 10,
                // bottom
                9, 8, 12,
                // top
                12, 10, 11,
        };

        mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        mColorBuffer = RenderUtils.buildFloatBuffer(colors);
        mIndexBuffer = RenderUtils.buildByteBuffer(indices);

    }

    @Override
    /**
     * Used to render objects when working with OpenGL ES 2.x
     *
     * @param projectionMatrix The projection matrix obtained from the ARToolkit
     * @param modelViewMatrix  The marker transformation matrix obtained from ARToolkit
     */
    public void draw(float[] projectionMatrix, float[] modelViewMatrix) {
        shaderProgram.setProjectionMatrix(projectionMatrix);
        shaderProgram.setModelViewMatrix(modelViewMatrix);
        shaderProgram.render(this.getmVertexBuffer(), this.getmColorBuffer(), this.getmIndexBuffer());
    }

    @Override
    /**
     * Sets the shader program used by this geometry.
     */
    public void setShaderProgram(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }
}
