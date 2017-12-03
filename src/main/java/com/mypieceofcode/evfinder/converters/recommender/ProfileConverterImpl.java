package com.mypieceofcode.evfinder.converters.recommender;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProfileConverterImpl implements ProfileConverter {

    @Override
    public int[] convertJsonStringToArray(String json) {
        int array[] = null;
        try {
            Map<String, Integer> result = new ObjectMapper().readValue(json, HashMap.class);
            array =new int[result.size()+3];
            for (Map.Entry<String, Integer> stringLongEntry : result.entrySet()) {
                array[Integer.valueOf(stringLongEntry.getKey())] = stringLongEntry.getValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createValueOfMainCategory(array);
    }

    private int [] createValueOfMainCategory(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i > 0 && i < 12){
                if(isSelected(array[i])){
                    array[0] = 1;
                }
            }
            if (i > 12 && i < 23){
                if (isSelected(array[i])){
                    array[12] = 1;
                }
            }
            if (i > 24 && i < 31){
                if (isSelected(array[i])){
                    array[23] = 1;
                }
            }
        }
        return array;
    }

    private boolean isSelected(int i) {
        return i > 0;
    }
}
