#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL Java_com_example_makeinteract_MainActivity_stringFromJNI(JNIEnv* env, jobject /* this */) {
    std::string hello = "Hello from C++, my name is Aylmer";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL Java_com_example_makeinteract_MainActivity_process(JNIEnv* env, jobject mainActivityInstance /* this */) {
    const jclass mainActivityCls = env->GetObjectClass(mainActivityInstance);
    const jmethodID jmethodId = env->GetMethodID(mainActivityCls,"processInJava","()Ljava/lang/String;");
    JNINativeMethod method = {};
    if(jmethodId == nullptr){
        return env->NewStringUTF("");
    }
    const jobject result = env->CallObjectMethod(mainActivityInstance, jmethodId);
    const std::string java_msg = env->GetStringUTFChars((jstring)result, JNI_FALSE);
    const std::string c_msg = "Result from java => ";
    const std::string msg = c_msg + java_msg;
    return  env->NewStringUTF(msg.c_str());
}

extern "C" JNIEXPORT jstring JNICALL Java_com_example_makeinteract_MainActivity_printnumber(JNIEnv* env, jobject thisObj, jint num) {

    jclass  resClass = env->FindClass("com/example/makeinteract/MainActivity");
    jmethodID resMethodID = env->GetMethodID(resClass, "res", "(I)Ljava/lang/String;");

    return (jstring)env->CallObjectMethod(thisObj, resMethodID, num);


}