package ittimfn.tool.fullpatterntable.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Data
public class ConvertController {

    private static Logger logger = LogManager.getLogger();

    private List<List<String>> argsItemList;
    
    private int[] indexs;
    public ConvertController() {
        this.argsItemList = new ArrayList<List<String>>();
        this.indexs = new int[0];
    }

    public ConvertController(String[] importFilePaths) throws IOException {
        this.argsItemList = ConvertController.readFiles(importFilePaths);
        this.indexs = ConvertController.createIndexs(argsItemList.size());
    }

    private static List<List<String>> readFiles(String[] filepaths) throws IOException {
        List<List<String>> returnList = new ArrayList<List<String>>();
        for(String path : filepaths) {
            logger.info("path : {}", path);
            
            List<String> input = Files.lines(Paths.get(path)).collect(Collectors.toList());
            logger.debug("input : {}", input);

            returnList.add(input);
        }
        return returnList;
    }

    private static int[] createIndexs(int length) {
        logger.debug("indexs length : {}", length);

        int[] indexs = new int[length];
        for(int i = 0 ; i < length ; i++) {
            indexs[i] = 0;
        }
        logger.debug("indexs : {}", indexs);

        return indexs;
    }
    
    public List<String> convert() {
        List<String> convertedList = new ArrayList<String>();
        
        while(true) {
            String converted = this.convertToString();
            logger.trace(converted);
            convertedList.add(converted);

            logger.trace("indexs : {}", this.indexs);
            if(this.isFinish()) {
                break;
            } else {
                this.indexsUpdate();
            }
        }
        return convertedList;
    }

    private String convertToString() {
        StringJoiner joiner = new StringJoiner("\t");
        for(int i = 0 ; i < this.argsItemList.size() ; i++) {
            joiner.add(this.argsItemList.get(i).get(indexs[i]));
        }
        return joiner.toString();
    }

    private void indexsUpdate() {
        boolean update = true;
        for(int i = indexs.length - 1 ; i >= 0 ; i--) {
            update = this.countUpAndReset(i, update);
        }
    }

    /**
     * indexsをアップデートする。
     * @param depth TSVの列。0が左端。
     * @param update 自分の右の列が0になったかどうか。(=実際にカウントアップ処理を行う。)
     * @return depthの列のindexを0にしたかどうか。
     */
    private boolean countUpAndReset(int depth, boolean update) {
        if(update) {
            int index = this.indexs[depth];
            int itemMaxIndex = this.argsItemList.get(depth).size() - 1;
            boolean reset = index == itemMaxIndex;
            if(reset) {
                this.indexs[depth] = 0;
            } else {
                this.indexs[depth]++;
            }
            return reset;
        } else {
            return false;
        }
    }

    private boolean isFinish() {
        for(int i = 0 ; i < this.indexs.length ; i++) {
            if(this.indexs[i] < this.argsItemList.get(i).size() -1) {
                return false;
            }
        }
        return true;
    }
}
