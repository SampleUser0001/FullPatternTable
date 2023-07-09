package ittimfn.tool.fullpatterntable;

import java.io.IOException;

import ittimfn.tool.fullpatterntable.controller.ConvertController;

public class App {
    private void exec(String[] args) throws IOException {
        new ConvertController(args).convert().forEach(System.out::println);
    }
    
    public static void main( String[] args ) throws IOException {
        new App().exec(args);
    }
}
