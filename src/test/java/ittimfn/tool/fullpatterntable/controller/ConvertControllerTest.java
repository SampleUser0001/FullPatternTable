package ittimfn.tool.fullpatterntable.controller;

import java.util.List;
import java.util.ArrayList;

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
    
    @Test
    public void isFinish_not_enough_is_false() {
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
    
}
