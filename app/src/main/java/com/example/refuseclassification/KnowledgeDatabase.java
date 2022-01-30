package com.example.refuseclassification;

import com.example.refuseclassification.Database.Knowledge;

import org.litepal.LitePal;
import java.util.List;

public class KnowledgeDatabase {

    String[] name = {"菠萝", "鸭脖", "鸭脖子", "萝卜", "胡萝卜", "萝卜干", "草", "草莓",
                     "红肠", "香肠", "鱼肠", "过期食品", "剩菜剩饭", "死老鼠", "麦丽素", "果粒",
                     "巧克力", "芦荟", "落叶", "乳制品", "鲜肉月饼", "炸鸡腿", "药渣", "毛毛虫", "蜗牛",
                     "安全套", "按摩棒", "肮脏塑料袋", "旧扫把", "旧拖把", "搅拌棒", "棒骨", "蚌壳",
                     "保龄球", "爆竹", "纸杯", "扇贝", "鼻毛", "鼻屎", "笔", "冥币",
                     "尿不湿", "餐巾", "餐纸", "一次性叉子", "掉下来的牙齿", "丁字裤", "耳屎", "飞机杯", "碱性无汞电池",
                     "安全帽", "棉袄", "白纸", "手办", "包包", "保温杯", "报纸", "电脑设备",
                     "被单", "本子", "手表", "玻璃", "尺子", "充电宝", "充电器", "空调",
                     "耳机", "衣服", "乐高", "公仔", "可降解塑料", "酒瓶", "篮球", "红领巾", "泡沫箱",
                     "阿司匹林", "浴霸灯泡", "避孕药", "温度计", "杀毒剂", "感冒药", "药瓶", "止咳糖浆",
                     "胶囊", "灯泡", "农药", "油漆", "维生素", "酒精", "指甲油", "铅蓄电池",
                     "废电池", "打火机", "医用纱布", "医用棉签", "相片", "干电池", "钙片", "针管", "针筒"};

    String[] kind = {"湿垃圾", "干垃圾", "可回收物", "有害垃圾"};

    public void setKnowledgeDatabase() {
        LitePal.getDatabase();
        for (int i = 0; i < 100; i++) {
            // 获取数据表数据,查询是否有相同数据，防止重复插入
            List<Knowledge> knowledges = LitePal.where("id = ?", String.valueOf(i + 1))
                    .find(Knowledge.class);
            if (knowledges == null || knowledges.size()== 0)
                if (i < 25)
                    insert(i + 1, name[i], kind[0]);
                else if (i < 50)
                    insert(i + 1, name[i], kind[1]);
                else if (i < 75)
                    insert(i + 1, name[i], kind[2]);
                else
                    insert(i + 1, name[i],  kind[3]);
            else
                continue;
        }
    }

    public void insert(int id, String name, String kind) {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(id);
        knowledge.setName(name);
        knowledge.setKind(kind);
        knowledge.save();
    }
}
