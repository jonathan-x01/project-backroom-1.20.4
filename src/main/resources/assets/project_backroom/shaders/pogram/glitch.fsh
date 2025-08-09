#version 150

uniform sampler2D Sampler0;
uniform float Time;
uniform float TearStrength;
uniform float TearSpeed;
uniform float RowCount;

in vec2 texCoord0;
out vec4 fragColor;

// Fast hash function for randomness
float hash(float x) {
    return fract(sin(x * 123.456 + Time * TearSpeed) * 43758.5453);
}

void main() {
    vec2 uv = texCoord0;

    float row = floor(uv.y * RowCount);
    float offset = (hash(row) * 2.0 - 1.0) * TearStrength;

    uv.x += offset;

    fragColor = texture(Sampler0, uv);
}