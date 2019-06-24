/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/7 19:38</create-date>
 *
 * <copyright file="DemoJapaneseNameRecognition.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.daixinmini.video.demo;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * 日本人名识别
 * @author hankcs
 */
public class DemoJapaneseNameRecognition
{
    public static void main(String[] args)
    {
//        String[] testCase = new String[]{
//                "北川景子参演了林诣彬导演的《速度与激情3》",
//                "林志玲亮相网友:确定不是波多野结衣？",
//                "龟山千广和近藤公园在龟山公园里喝酒赏花",
//        };
        String[] testCase = new String[]{
                "村上春树",
                "毛雯雯",
                "龟山千广",
                "代鑫",
                "聂梦茜",
                "李国易",
                "方尚",
                "魏振宇",
                "张雄",
                "Michael Jackson",
                "Daisy",
                "陈震",
                "苗",
                "诸葛亮",
                "司马懿"
        };
        Segment segment = HanLP.newSegment().enableAllNamedEntityRecognize(true);

        for (String sentence : testCase)
        {
            List<Term> termList = segment.seg(sentence);
            if (termList.size() > 1) {
                System.out.println("first:");
                System.out.println(termList.get(0).word);
            } else {
                List<Term> segment1 = HanLP.segment(termList.get(0).word);
                if (termList.size() > 1) {
                    System.out.println("first:");
                    System.out.println(segment1.get(0).word);
                } else {
                    System.out.println("second:");
                    System.out.println(segment1);
                }

            }

        }
    }
}
