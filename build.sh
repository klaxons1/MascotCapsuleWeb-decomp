#!/bin/sh
set -e

JAVAC="/tmp/jdk1.6.0_45/bin/javac"
JAR="/tmp/jdk1.6.0_45/bin/jar"

if [ ! -x "$JAVAC" ]; then
    JAVAC="javac"
    JAR="jar"
fi

echo "Using compiler: $($JAVAC -version 2>&1)"

mkdir -p build/classes
$JAVAC -d build/classes $(find src -name "*.java")

# Copy non-java resource files (images, configs) to build/classes
cp src/*.gif build/classes/ 2>/dev/null || true

$JAR cfm build/MascotCapsule.jar src/META-INF/MANIFEST.MF -C build/classes .

echo "Build successful -> build/MascotCapsule.jar"
