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

/**
 * Simple class to render a primitive cat.
 */
public class Cat implements ARDrawable {

    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;
    private ShaderProgram shaderProgram;
    public int it = 0;
    private float mx, my, mz, msize;
    @SuppressWarnings("unused")
    public Cat() {
        this(1.0f);
    }

    @SuppressWarnings("WeakerAccess")
    public Cat(float size) {
        this(size, 0.0f, 0.0f, 0.0f);
    }

    public Cat(float size, float x, float y, float z) {
        mx = x;
        my = y;
        mz = z;
        msize = size;
        setArrays(size, x, y, z);
    }

    @SuppressWarnings("unused")
    public Cat(ShaderProgram shaderProgram) {
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
        float headsize = size / 3.0f;
        float earsize = headsize / 4.0f;
        float eyesize = headsize / 6.0f;
        float vertices[] = {
                // body
                x - hs, y - hs, z - hs, // 0 --> back bottom left
                x + hs, y - hs, z - hs, // 1 --> back bottom right
                x + hs, y - hs, z + hs, // 2 --> front right bottom
                x - hs, y - hs, z + hs, // 3 --> front left bottom
                x, y + hs, z - hs, // 4 --> That is the top back.
                x, y + hs, z + hs, // 5 ---> top front.
                // head
                x - headsize, y + hs + headsize, z - hs, // 6 --> back head left
                x - headsize, y + hs + headsize, z + hs, // 7 --> front head left
                x + headsize, y + hs + headsize, z - hs, // 8 --> back head right
                x + headsize, y + hs + headsize, z + hs, // 9 --> front head right
                // tail pointing right
                x + hs + headsize, y + headsize, z - hs, // 10 --> front right tail
                x + hs, y + headsize + headsize / 2.0f, z - hs, // 11 --> front left tail
                x + hs + headsize, y + headsize, z, // 12 --> back of tail
                // left ear
                x - 3*earsize, y + hs + headsize, z - earsize, // 13 --> back bottom left
                x - earsize, y + hs + headsize, z - earsize, // 14 --> back bottom right
                x - earsize, y + hs + headsize, z + earsize, // 15 --> front right bottom
                x - 3 * earsize, y + hs + headsize, z + earsize, // 16 --> front left bottom
                x - 2 * earsize, y + hs + headsize + 2*earsize, z, // 17 --> top
                // right ear
                x + 3*earsize, y + hs + headsize, z - earsize, // 18 --> back bottom left
                x + earsize, y + hs + headsize, z - earsize, // 19 --> back bottom right
                x + earsize, y + hs + headsize, z + earsize, // 20 --> front right bottom
                x + 3 * earsize, y + hs + headsize, z + earsize, // 21 --> front left bottom
                x + 2 * earsize, y + hs + headsize + 2*earsize, z, // 22 --> top

                // left eye
                x - earsize, y + hs + headsize / 2.0f, z + hs, // 23 --> front left
                x - earsize + 2*eyesize, y + hs + headsize / 2.0f, z + hs, // 24 --> front right
                x - earsize + eyesize, y + hs + headsize / 2.0f - eyesize, z + hs, // 25 --> front left

        };
        float c = 1.0f;
        float colors[] = {
                1.0f, 0.5f, 0, c, // 0
                1.0f, 1.0f, 0, c, // 1
                1.0f, 0.5f, 0, c, // 2
                11.0f, 1.0f, 0, c, // 3
                1.0f, 0.5f, 0, c, // 4
                1.0f, 1.0f, 0, c, // 5
                1.0f, 0.5f, 0, c, // 6
                1.0f, 1.0f, 0, c, // 7
                1.0f, 0.5f, 0, c, // 8
                1.0f, 1.0f, 0, c, // 9
                1.0f, 0.5f, 0, c, // 10
                1.0f, 1.0f, 0, c, // 11
                1.0f, 0.5f, 0, c, // 12
                1.0f, 1.0f, 0, c, // 13
                1.0f, 0.5f, 0, c, // 14
                1.0f, 1.0f, 0, c, // 15
                1.0f, 0.5f, 0, c, // 16
                1.0f, 1.0f, 0, c, // 17
                1.0f, 0.5f, 0, c, // 18
                1.0f, 1.0f, 0, c, // 19
                1.0f, 0.5f, 0, c, // 20
                1.0f, 1.0f, 0, c, // 21
                1.0f, 0.5f, 0, c, // 22

                0.0f, 0.0f, 0, c, // 23
                0.0f, 0.0f, 0, c, // 24
                0.0f, 0.0f, 0, c, // 25
        };

        byte indices[] = {
                // body
                // bottom
                2, 0, 1,
                3, 0, 2,
                // front
                0, 1, 4,
                //back
                3, 2, 5,
                //left
                0, 3, 4,
                3, 5, 4,
                //right
                1, 4, 2,
                2, 4, 5,

                // head
                // front
                5, 9, 7,
                // back
                4, 8, 6,
                // left
                7, 6, 4,
                5, 7, 4,
                // right
                4, 8, 9,
                4, 9, 5,
                // top
                9, 8, 6,
                9, 6, 7,

                // tail
                // front
                1, 10, 11,
                // right
                12, 11, 10,
                // bottom
                12, 10, 1,
                // back
                11, 12, 1,

                // left ear
                14, 17, 15, // right
                14, 17, 13, // back
                16, 17, 13, // left
                15, 17, 16, // front

                // right ear
                20, 22, 19, // right
                18, 22, 19, // back
                18, 22, 21, // left
                21, 22, 20, // front

                // mouth
                25,24,23,
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

        float c = 1.0f;

        it+=1;

        if (it < 25) {
            float x, y, z, hs;
            mx = x = mx - 1;
            y = my;
            z = mz;
            hs = msize / 2.0f;
            float headsize = msize / 3.0f;
            float earsize = headsize / 4.0f;
            float eyesize = headsize / 6.0f;
            float vertices[] = {
                    // body
                    x - hs, y - hs, z - hs, // 0 --> back bottom left
                    x + hs, y - hs, z - hs, // 1 --> back bottom right
                    x + hs, y - hs, z + hs, // 2 --> front right bottom
                    x - hs, y - hs, z + hs, // 3 --> front left bottom
                    x, y + hs, z - hs, // 4 --> That is the top back.
                    x, y + hs, z + hs, // 5 ---> top front.
                    // head
                    x - headsize, y + hs + headsize, z - hs, // 6 --> back head left
                    x - headsize, y + hs + headsize, z + hs, // 7 --> front head left
                    x + headsize, y + hs + headsize, z - hs, // 8 --> back head right
                    x + headsize, y + hs + headsize, z + hs, // 9 --> front head right
                    // tail pointing right
                    x + hs + headsize, y + headsize, z - hs, // 10 --> front right tail
                    x + hs, y + headsize + headsize / 2.0f, z - hs, // 11 --> front left tail
                    x + hs + headsize, y + headsize, z, // 12 --> back of tail
                    // left ear
                    x - 3 * earsize, y + hs + headsize, z - earsize, // 13 --> back bottom left
                    x - earsize, y + hs + headsize, z - earsize, // 14 --> back bottom right
                    x - earsize, y + hs + headsize, z + earsize, // 15 --> front right bottom
                    x - 3 * earsize, y + hs + headsize, z + earsize, // 16 --> front left bottom
                    x - 2 * earsize, y + hs + headsize + 2 * earsize, z, // 17 --> top
                    // right ear
                    x + 3 * earsize, y + hs + headsize, z - earsize, // 18 --> back bottom left
                    x + earsize, y + hs + headsize, z - earsize, // 19 --> back bottom right
                    x + earsize, y + hs + headsize, z + earsize, // 20 --> front right bottom
                    x + 3 * earsize, y + hs + headsize, z + earsize, // 21 --> front left bottom
                    x + 2 * earsize, y + hs + headsize + 2 * earsize, z, // 22 --> top

                    // left eye
                    x - earsize, y + hs + headsize / 2.0f, z + hs, // 23 --> front left
                    x - earsize + 2 * eyesize, y + hs + headsize / 2.0f, z + hs, // 24 --> front right
                    x - earsize + eyesize, y + hs + headsize / 2.0f - eyesize, z + hs, // 25 --> front left

            };
            mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        }
        else if (it >= 25 && it < 45) {
            float x, y, z, hs;
            x = mx;
            y = my;
            z = mz;
            hs = msize / 2.0f;
            float headsize = msize / 3.0f;
            float earsize = headsize / 4.0f;
            float eyesize = headsize / 6.0f;
            float vertices[] = {
                    // body
                    x - hs, y - hs, z - hs, // 0 --> back bottom left
                    x + hs, y - hs, z - hs, // 1 --> back bottom right
                    x + hs, y - hs, z + hs, // 2 --> front right bottom
                    x - hs, y - hs, z + hs, // 3 --> front left bottom
                    x, y + hs, z - hs, // 4 --> That is the top back.
                    x, y + hs, z + hs, // 5 ---> top front.
                    // head
                    x - headsize, y + hs + headsize, z - hs, // 6 --> back head left
                    x - headsize, y + hs + headsize, z + hs, // 7 --> front head left
                    x + headsize, y + hs + headsize, z - hs, // 8 --> back head right
                    x + headsize, y + hs + headsize, z + hs, // 9 --> front head right
                    // tail pointing left
                    x - hs - headsize, y + headsize, z - hs, // 10 --> front right tail
                    x - hs, y + headsize + headsize / 2.0f, z - hs, // 11 --> front left tail
                    x - hs - headsize, y + headsize, z, // 12 --> back of tail
                    // left ear
                    x - 3 * earsize, y + hs + headsize, z - earsize, // 13 --> back bottom left
                    x - earsize, y + hs + headsize, z - earsize, // 14 --> back bottom right
                    x - earsize, y + hs + headsize, z + earsize, // 15 --> front right bottom
                    x - 3 * earsize, y + hs + headsize, z + earsize, // 16 --> front left bottom
                    x - 2 * earsize, y + hs + headsize + 2 * earsize, z, // 17 --> top
                    // right ear
                    x + 3 * earsize, y + hs + headsize, z - earsize, // 18 --> back bottom left
                    x + earsize, y + hs + headsize, z - earsize, // 19 --> back bottom right
                    x + earsize, y + hs + headsize, z + earsize, // 20 --> front right bottom
                    x + 3 * earsize, y + hs + headsize, z + earsize, // 21 --> front left bottom
                    x + 2 * earsize, y + hs + headsize + 2 * earsize, z, // 22 --> top

                    // left eye
                    x - earsize, y + hs + headsize / 2.0f, z + hs, // 23 --> front left
                    x - earsize + 2 * eyesize, y + hs + headsize / 2.0f, z + hs, // 24 --> front right
                    x - earsize + eyesize, y + hs + headsize / 2.0f - eyesize, z + hs, // 25 --> front left
            };
            byte indices[] = {
                    // body
                    // bottom
                    2, 0, 1,
                    3, 0, 2,
                    // front
                    0, 1, 4,
                    //back
                    3, 2, 5,
                    //left
                    0, 3, 4,
                    3, 5, 4,
                    //right
                    1, 4, 2,
                    2, 4, 5,

                    // head
                    // front
                    5, 9, 7,
                    // back
                    4, 8, 6,
                    // left
                    7, 6, 4,
                    5, 7, 4,
                    // right
                    4, 8, 9,
                    4, 9, 5,
                    // top
                    9, 8, 6,
                    9, 6, 7,

                    // tail
                    // front
                    1, 10, 11,
                    // right
                    12, 11, 10,
                    // bottom
                    12, 10, 1,
                    // back
                    11, 12, 1,

                    // left ear
                    14, 17, 15, // right
                    14, 17, 13, // back
                    16, 17, 13, // left
                    15, 17, 16, // front

                    // right ear
                    20, 22, 19, // right
                    18, 22, 19, // back
                    18, 22, 21, // left
                    21, 22, 20, // front

                    // mouth
                    25,24,23,
            };
            mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
            mIndexBuffer = RenderUtils.buildByteBuffer(indices);
        }
        else if (it >= 45 && it < 70) {
            float x, y, z, hs;
            x = mx;
            my = y = my + 1;
            z = mz;
            hs = msize / 2.0f;
            float headsize = msize / 3.0f;
            float earsize = headsize / 4.0f;
            float eyesize = headsize / 6.0f;
            float vertices[] = {
                    // body
                    x - hs, y - hs, z - hs, // 0 --> back bottom left
                    x + hs, y - hs, z - hs, // 1 --> back bottom right
                    x + hs, y - hs, z + hs, // 2 --> front right bottom
                    x - hs, y - hs, z + hs, // 3 --> front left bottom
                    x, y + hs, z - hs, // 4 --> That is the top back.
                    x, y + hs, z + hs, // 5 ---> top front.
                    // head
                    x - headsize, y + hs + headsize, z - hs, // 6 --> back head left
                    x - headsize, y + hs + headsize, z + hs, // 7 --> front head left
                    x + headsize, y + hs + headsize, z - hs, // 8 --> back head right
                    x + headsize, y + hs + headsize, z + hs, // 9 --> front head right
                    // tail pointing right
                    x + hs + headsize, y + headsize, z - hs, // 10 --> front right tail
                    x + hs, y + headsize + headsize / 2.0f, z - hs, // 11 --> front left tail
                    x + hs + headsize, y + headsize, z, // 12 --> back of tail
                    // left ear
                    x - 3 * earsize, y + hs + headsize, z - earsize, // 13 --> back bottom left
                    x - earsize, y + hs + headsize, z - earsize, // 14 --> back bottom right
                    x - earsize, y + hs + headsize, z + earsize, // 15 --> front right bottom
                    x - 3 * earsize, y + hs + headsize, z + earsize, // 16 --> front left bottom
                    x - 2 * earsize, y + hs + headsize + 2 * earsize, z, // 17 --> top
                    // right ear
                    x + 3 * earsize, y + hs + headsize, z - earsize, // 18 --> back bottom left
                    x + earsize, y + hs + headsize, z - earsize, // 19 --> back bottom right
                    x + earsize, y + hs + headsize, z + earsize, // 20 --> front right bottom
                    x + 3 * earsize, y + hs + headsize, z + earsize, // 21 --> front left bottom
                    x + 2 * earsize, y + hs + headsize + 2 * earsize, z, // 22 --> top

                    // left eye
                    x - earsize, y + hs + headsize / 2.0f, z + hs, // 23 --> front left
                    x - earsize + 2 * eyesize, y + hs + headsize / 2.0f, z + hs, // 24 --> front right
                    x - earsize + eyesize, y + hs + headsize / 2.0f - eyesize, z + hs, // 25 --> front left

            };
            mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        }
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
