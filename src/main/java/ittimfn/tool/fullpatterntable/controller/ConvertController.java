package ittimfn.tool.fullpatterntable.controller;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ConvertController {
    private List<List<String>> argsItemList;
    
    private int[] indexs;
    public ConvertController() {
        this.argsItemList = new ArrayList<List<String>>();
        this.indexs = new int[0];
    }

    public ConvertController(String[] importFilePaths) throws IOException {
        for(String path : importFilePaths) {
            this.argsItemList.add(Files.lines(Paths.get(path)).collect(Collectors.toList()));
        }
        this.indexs = new int[argsItemList.size()];
        for(int i = 0 ; i < this.indexs.length ; i++) {
            this.indexs[i] = 0;
        }
    }
    
    public List<String> convert() {
        List<String> convertedList = new ArrayList<String>();
        
        return convertedList;
    }
    
    public boolean isFinish() {
        for(int i = 0 ; i < this.indexs.length ; i++) {
            if(this.indexs[i] < this.argsItemList.get(i).size() -1) {
                return false;
            }
        }
        return true;
    }
}
