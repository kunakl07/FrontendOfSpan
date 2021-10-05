package com.span.pygen.emitter.nodehandlers.contexts;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps a track of lattices that this encountered
 */
public class LatticeDeclContextBuffer {
    private static LatticeDeclContextBuffer latticeContextBuffer;
    private List<String> lattices;
    private LatticeDeclContextBuffer(){
        this.lattices = new ArrayList<>();
    }

    public static LatticeDeclContextBuffer getInstance(){
        if(latticeContextBuffer == null)
            latticeContextBuffer = new LatticeDeclContextBuffer();
        return latticeContextBuffer;
    }

    public void add(String lattice){
        this.lattices.add(lattice);
    }

    public List<String> getLattices(){
        return this.lattices;
    }

    public boolean contains(String lattice){
        return this.lattices.contains(lattice);
    }

}
