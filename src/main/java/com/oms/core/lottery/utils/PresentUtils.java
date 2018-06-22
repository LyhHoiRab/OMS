package com.oms.core.lottery.utils;

import com.oms.core.lottery.entity.Present;
import lombok.NoArgsConstructor;
import org.wah.doraemon.security.exception.UtilsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class PresentUtils{

    public Present random(List<Present> presents){
        if(presents == null || presents.isEmpty()){
            throw new UtilsException("奖品列表不能为空");
        }

        int size = presents.size();

        double sumRate = 0d;
        for(Present present : presents){
            sumRate += present.getProbability();
        }

        List<Double> sortOrignalRates = new ArrayList<Double>(size);
        Double tempSumRate = 0d;
        for(Present present : presents){
            tempSumRate += present.getProbability();
            sortOrignalRates.add(tempSumRate / sumRate);
        }

        double nextDouble = Math.random();
        sortOrignalRates.add(nextDouble);
        Collections.sort(sortOrignalRates);

        return presents.get(sortOrignalRates.indexOf(nextDouble));
    }
}
