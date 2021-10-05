package com.span.pygen.emitter.nodehandlers.contexts;

import com.span.pygen.codebuilder.Class;

import java.util.HashMap;
import java.util.Optional;

/**
 * A singleton that stores intermediate builder objects.
 * NOTE- This IS NOT THREAD SAFE
 */
public class ClassContextBuffer {
    private static ClassContextBuffer contextBuffer;

    private HashMap<String, Class> classHashMap;
    private ClassContextBuffer(){
        this.classHashMap = new HashMap<>();
    }

    public static ClassContextBuffer getInstance(){
        if(ClassContextBuffer.contextBuffer == null)
            contextBuffer = new ClassContextBuffer();
        return contextBuffer;
    }

    public void addClass(Class k){
        this.classHashMap.put(k.getClassName(), k);
    }

    public Optional<Class> getClass(String className){
        return Optional.ofNullable(this.classHashMap.get(className));
    }

    public void finalize(){
        this.classHashMap.clear();
    }

}
