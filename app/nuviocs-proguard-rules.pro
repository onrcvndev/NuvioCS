# Nuvio CS fork R8 rules, added to every build type from nuviocs.gradle.

# sherpa-onnx (audio subtitle sync speech recognition): JNI reads config fields by name.
-keep class com.k2fsa.sherpa.onnx.** { *; }
-keep interface com.k2fsa.sherpa.onnx.** { *; }
