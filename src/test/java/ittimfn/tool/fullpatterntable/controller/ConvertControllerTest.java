package ittimfn.tool.fullpatterntable.controller;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ConvertControllerTest {
    private ConvertController testComponent;

    @BeforeEach
    public void setup() {
        testComponent = new ConvertController();
    }
    
    /**
     * isFinishのテスト
     * 要素数 : 1, index : 未到達
     */
    @Test
    public void isFinish_not_enough_is_false_single() {
        List<List<String>> argsList = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("hoge");
        argsList.add(list);
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[1];
        indexs[0] = 0;
        testComponent.setIndexs(indexs);
        
        assertThat(testComponent.isFinish(), is(false));
    }

    /**
     * isFinishnテスト
     * 要素数 : 1, index : 到達
     */
    @Test
    public void isFinish_enough_is_true_single() {
        List<List<String>> argsList = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("hoge");
        argsList.add(list);
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[1];
        indexs[0] = 1;
        testComponent.setIndexs(indexs);
        
        assertThat(testComponent.isFinish(), is(true));
    }

    /**
     * isFinishnテスト
     * 要素数 : 1, index : 超過
     */
    @Test
    public void isFinish_over_is_true_single() {
        List<List<String>> argsList = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("hoge");
        argsList.add(list);
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[1];
        indexs[0] = 2;
        testComponent.setIndexs(indexs);
        
        assertThat(testComponent.isFinish(), is(true));
    }
    
    @Test
    public void failTest() {
        fail("fail Test");
    }
}
