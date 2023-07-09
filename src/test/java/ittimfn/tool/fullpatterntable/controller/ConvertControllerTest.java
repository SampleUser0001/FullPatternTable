package ittimfn.tool.fullpatterntable.controller;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ConvertControllerTest {
    private ConvertController testComponent;

    private Method isFinish;
    private Method countUpAndReset;
    private Method indexsUpdate;
    private Method convertToString;
    
    @BeforeEach
    public void setup() throws NoSuchMethodException, SecurityException {
        testComponent = new ConvertController();

        isFinish = ConvertController.class.getDeclaredMethod("isFinish");
        isFinish.setAccessible(true);

        countUpAndReset = ConvertController.class.getDeclaredMethod("countUpAndReset", int.class, boolean.class);
        countUpAndReset.setAccessible(true);

        indexsUpdate = ConvertController.class.getDeclaredMethod("indexsUpdate");
        indexsUpdate.setAccessible(true);

        convertToString = ConvertController.class.getDeclaredMethod("convertToString");
        convertToString.setAccessible(true);
    }
    
    /**
     * isFinishのテスト
     * 要素数 : 2, index : 未到達
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void isFinish_not_enough_is_false_single() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[1];
        indexs[0] = 0;
        testComponent.setIndexs(indexs);
        
        assertThat(isFinish.invoke(testComponent), is(false));
    }

    /**
     * isFinishテスト
     * 要素数 : 1, index : 到達
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void isFinish_enough_is_true_single() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[1];
        indexs[0] = 1;
        testComponent.setIndexs(indexs);
        
        assertThat(isFinish.invoke(testComponent), is(true));
    }

    /**
     * isFinishnテスト
     * 要素数 : 1, index : 超過
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void isFinish_over_is_true_single() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[1];
        indexs[0] = 2;
        testComponent.setIndexs(indexs);
        
        assertThat(isFinish.invoke(testComponent), is(true));
    }

    /**
     * isFinishのテスト
     * 要素数 : 2,
     * index[0] : 未到達
     * index[1] : 未到達
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void isFinish_double_false_01() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[2];
        indexs[0] = 0;
        indexs[1] = 0;
        testComponent.setIndexs(indexs);
        
        assertThat(isFinish.invoke(testComponent), is(false));
    }

    /**
     * isFinishのテスト
     * 要素数 : 2,
     * index[0] : 未到達
     * index[1] : 到達
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void isFinish_double_false_02() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[2];
        indexs[0] = 0;
        indexs[1] = 1;
        testComponent.setIndexs(indexs);
        
        assertThat(isFinish.invoke(testComponent), is(false));
    }

    /**
     * isFinishのテスト
     * 要素数 : 2,
     * index[0] : 到達
     * index[1] : 未到達
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void isFinish_double_false_03() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[2];
        indexs[0] = 1;
        indexs[1] = 0;
        testComponent.setIndexs(indexs);
        
        assertThat(isFinish.invoke(testComponent), is(false));
    }

    /**
     * isFinishのテスト
     * 要素数 : 2,
     * index[0] : 到達
     * index[1] : 到達
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void isFinish_double_true() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);
        
        int[] indexs = new int[2];
        indexs[0] = 1;
        indexs[1] = 1;
        testComponent.setIndexs(indexs);
        
        assertThat(isFinish.invoke(testComponent), is(true));
    }

    /**
     * update = false
     * indexs = countUp
     * result : 更新されない
     * return : false
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void countUpAndReset_not_update_01() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));

        int[] indexs = new int[argsList.size()];
        indexs[0] = 0;

        boolean reset = (boolean) countUpAndReset.invoke(testComponent, 0, false);

        assertThat(reset, is(false));
        assertThat(indexs[0], is(equalTo(0)));
    }

    /**
     * update = false
     * indexs = reset
     * result : 更新されない
     * return : false
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void countUpAndReset_not_update_02() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));

        int[] indexs = new int[argsList.size()];
        indexs[0] = 1;

        boolean reset = (boolean) countUpAndReset.invoke(testComponent, 0, false);

        assertThat(reset, is(false));
        assertThat(indexs[0], is(equalTo(1)));
    }

    /**
     * update = true
     * indexs = update
     * result : +1
     * return : false
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void countUpAndReset_update() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);

        int[] indexs = new int[argsList.size()];
        indexs[0] = 0;
        testComponent.setIndexs(indexs);

        boolean reset = (boolean) countUpAndReset.invoke(testComponent, 0, true);

        assertThat(reset, is(false));
        assertThat(indexs[0], is(equalTo(1)));
    }

    /**
     * update = true
     * indexs = reset
     * result : 0
     * return : true
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void countUpAndReset_reset() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsList = new ArrayList<List<String>>();
        argsList.add(this.createList(2));
        testComponent.setArgsItemList(argsList);

        int[] indexs = new int[argsList.size()];
        indexs[0] = 1;
        testComponent.setIndexs(indexs);

        boolean reset = (boolean) countUpAndReset.invoke(testComponent, 0, true);

        assertThat(reset, is(true));
        assertThat(indexs[0], is(equalTo(0)));
    }

    /**
     * indexsUpdateのテスト
     * items : [01,02][01,02]
     * index : [0,0]
     * result : [0,1]
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void indexsUpdate_00_to_01() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsItemList = new ArrayList<List<String>>();
        argsItemList.add(this.createList(2));
        argsItemList.add(this.createList(2));
        testComponent.setArgsItemList(argsItemList);

        int[] indexs = new int[argsItemList.size()];
        indexs[0] = 0;
        indexs[1] = 0;
        testComponent.setIndexs(indexs);

        indexsUpdate.invoke(testComponent);

        assertArrayEquals(new int[]{0,1}, testComponent.getIndexs());
    }

    /**
     * indexsUpdateのテスト
     * items : [01,02][01,02]
     * index : [0,1]
     * result : [1,0]
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void indexsUpdate_01_to_10() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsItemList = new ArrayList<List<String>>();
        argsItemList.add(this.createList(2));
        argsItemList.add(this.createList(2));
        testComponent.setArgsItemList(argsItemList);

        int[] indexs = new int[argsItemList.size()];
        indexs[0] = 0;
        indexs[1] = 1;
        testComponent.setIndexs(indexs);

        indexsUpdate.invoke(testComponent);

        assertArrayEquals(new int[]{1,0}, testComponent.getIndexs());
    }

    /**
     * indexsUpdateのテスト
     * items : [01,02][01,02]
     * index : [1,0]
     * result : [1,1]
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void indexsUpdate_10_to_11() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsItemList = new ArrayList<List<String>>();
        argsItemList.add(this.createList(2));
        argsItemList.add(this.createList(2));
        testComponent.setArgsItemList(argsItemList);

        int[] indexs = new int[argsItemList.size()];
        indexs[0] = 1;
        indexs[1] = 0;
        testComponent.setIndexs(indexs);

        indexsUpdate.invoke(testComponent);

        assertArrayEquals(new int[]{1,1}, testComponent.getIndexs());
    }

    /**
     * countUpAndResetのテスト
     * indexsUpdateで下記のパターンがNGになったため、追加確認。
     * items : [01,02][01,02]
     * index : [0,1]
     * result : [1,0]
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Test
    public void countUpAndReset_extra01() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsItemList = new ArrayList<List<String>>();
        argsItemList.add(this.createList(2));
        argsItemList.add(this.createList(2));
        testComponent.setArgsItemList(argsItemList);

        int[] indexs = new int[argsItemList.size()];
        indexs[0] = 0;
        indexs[1] = 1;
        testComponent.setIndexs(indexs);

        boolean update = (boolean) countUpAndReset.invoke(testComponent, 1, true);
        countUpAndReset.invoke(testComponent, 0, update);
        assertArrayEquals(new int[]{1,0}, testComponent.getIndexs());
    }

    @Test
    public void convertToString_00_01() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<List<String>> argsItemList = new ArrayList<List<String>>();
        argsItemList.add(this.createList(2));
        argsItemList.add(this.createList(2));
        testComponent.setArgsItemList(argsItemList);

        int[] indexs = new int[argsItemList.size()];
        indexs[0] = 0;
        indexs[1] = 1;
        testComponent.setIndexs(indexs);

        String actual = (String) convertToString.invoke(testComponent);
        
        assertThat("00\t01", is(equalTo(actual)));
    }

    @Test
    public void convert_test_01() {
        List<List<String>> argsItemList = new ArrayList<List<String>>();
        argsItemList.add(this.createList(2));
        argsItemList.add(this.createList(2));
        testComponent.setArgsItemList(argsItemList);

        int[] indexs = new int[argsItemList.size()];
        indexs[0] = 0;
        indexs[1] = 0;
        testComponent.setIndexs(indexs);

        List<String> actual = testComponent.convert();
        
        assertArrayEquals(
            new String[]{"00\t00","00\t01","01\t00", "01\t01"},
            actual.toArray(new String[0]));
    }
    @Test
    @Disabled
    public void failTest() {
        fail("fail Test");
    }

    private List<String> createList(int size) {
        List<String> returnList = new ArrayList<String>(size);
        for(int i = 0 ; i < size ; i++) {
            returnList.add(String.format("%02d",i));
        }
        return returnList;
    }
}
