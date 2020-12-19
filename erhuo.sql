/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.32 : Database - erhuo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`erhuo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `erhuo`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(20) DEFAULT NULL,
  `school` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

/*Data for the table `address` */

insert  into `address`(`id`,`city`,`school`) values (1,'石家庄','河北师范大学'),(2,'石家庄','河北科技大学'),(3,'石家庄','河北医科大学'),(4,'石家庄','河北经贸大学'),(5,'石家庄','石家庄铁道大学'),(6,'保定','河北大学'),(7,'石家庄','石家庄学院'),(8,'石家庄','河北地质大学'),(9,'石家庄','河北师范大学汇华学院'),(10,'石家庄','河北师范大学附属民族学院'),(11,'石家庄','河北美术学院'),(12,'保定','华北电力大学保定校区'),(13,'保定','河北金融学院'),(14,'保定','河北农业大学'),(15,'保定','保定学院'),(16,'秦皇岛','燕山大学'),(17,'秦皇岛','河北环境工程学院'),(18,'秦皇岛','东北大学秦皇岛分校'),(19,'秦皇岛','河北科技师范学院'),(20,'秦皇岛','燕山大学里仁学院'),(21,'秦皇岛','河北对外经贸职业学院'),(22,'秦皇岛','秦皇岛职业技术学院'),(23,'秦皇岛','河北建材职业技术学院');

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values (1,'admin','666666');

/*Table structure for table `advertisement` */

DROP TABLE IF EXISTS `advertisement`;

CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `advertisement` */

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL COMMENT '评论的帖子的id',
  `last_id` int(11) DEFAULT NULL COMMENT '上一个回复的id',
  `message` varchar(200) NOT NULL,
  `user_id` int(11) NOT NULL,
  `last_user_id` int(11) DEFAULT NULL,
  `like_num` int(11) DEFAULT '0',
  PRIMARY KEY (`com_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4;

/*Data for the table `comment` */

insert  into `comment`(`com_id`,`post_id`,`last_id`,`message`,`user_id`,`last_user_id`,`like_num`) values (78,41,0,'66',123,0,1),(79,41,0,'99',119,0,0),(80,44,0,'确实不适合！',119,0,2),(81,44,80,'。。。。。',127,119,0),(82,45,0,'多少钱？',119,0,2),(83,44,0,'你好，请问你的位置是在哪里',119,0,0),(84,49,0,'你好你好',127,0,0),(85,50,0,'你好，用了多久？',119,0,1),(86,54,0,'好好看啊啊啊',130,0,1),(87,45,0,'你好呀',130,0,1),(88,53,0,'你好',136,0,1);

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `commodity_value` float DEFAULT NULL,
  `commodity_discount` int(11) DEFAULT NULL,
  `commodity_describe` varchar(300) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `oldStandard` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8mb4;

/*Data for the table `commodity` */

insert  into `commodity`(`id`,`user_id`,`commodity_value`,`commodity_discount`,`commodity_describe`,`type_id`,`time`,`oldStandard`) values (71,119,1500,0,'二手苹果11promax 256G  iphonexsmax 苹果xs，iPhonex 手机未拆无修，耳机充电器配件齐全，支持人脸识别！\n感兴趣的看下面第二张图片联系！',25,1608199963087,1),(72,124,200,0,'迪奥999 官方正品 转手原因，试色后不习惯',12,1608200153410,0),(73,124,150,0,'阿玛尼405 官网旗舰店购买 突然不想用了 还没用过',3,1608200290289,0),(74,119,2239,0,'VIVO X50 8+128G，液氧色，5G手机， \n带壳贴膜使用，保护完好，几乎全新\n使用感受 ：屏幕很舒服不刺眼，手机1750多克很轻\n转手原因 ：日常换机玩\n带原装快充充电器\n带充电器一副\r\n本交易仅支持自提、当面交易、邮寄',32,1608200392229,1),(75,119,2199,0,'OPPO reno梦镜版全面屏。6+128 功能全部正常，适合打游戏看电视运行很快，手感很好，，配件送:充电器，手机壳，插孔针 保修100天                                                      售后服务:非质量问题售出概不退换，不撕防伪标有质量问题三天可退换。\n顺丰+20元。中通，圆通包邮。 偏远地区邮费另算(比如新疆，西藏，内蒙古)',33,1608200577821,0),(76,124,100,0,'南极人棉服男2020款，xl号的，买小了，低价出',36,1608200637073,0),(77,124,120,0,'智铭，男服2030款黑色xl，买大了，低价出',35,1608200754932,0),(78,128,100,0,'十成新，只戴了一分钟\n感觉颜色不符合我\n感兴趣的话点“我想要”\n和我私聊吧～',31,1608200838510,0),(79,124,300,0,'自营富铤FORTEI羽绒服男士冬季潮流轻薄保暖连帽亮面韩版情侣款衣服休闲外套男装JSWZ2228黑色XL',35,1608200885997,1),(80,117,59,0,'ZARA【打折】TRF 女装 加大码风衣 078…\n颜色分类米色,尺码S-M (170/88A)\n二手物品不退换',40,1608200998778,1),(81,124,100,0,'休闲裤，低价出',44,1608200999417,1),(84,117,1100,0,'【官方正品】CHANEL 香奈儿炫亮魅力香水唇膏丝绒系列礼盒#72 #92  邂逅香水35ml，购于官网，全新，仅拆盒，低价出。一套出小样全送',23,1608201648962,0),(85,127,1000,0,'苹果7。32 G.想换新手机了，充电很快。电池效率98%没有基带。没有摄像头ID可退出很好用。可小刀。大刀勿来！',25,1608204967720,2),(86,117,30,0,'雅诗兰黛2018年圣诞礼盒眼影盘。一个是眼影加修容、一个眼影加高光。仅轻微摸过色。30一个包邮。两个55包邮。',21,1608205003977,1),(87,117,30,0,'完美日记眼影盘 完美日记眼小猪小狗猫咪小浣熊斑虎影盘闲置出 囤了很多盘，想  换点钱，割爱低价出，回点血啊～闲置全新的未拆封，拍下私聊我什么盘，可查验防伪码辨别真伪，\n需要的速买 ！！\n【一盘可包邮】\n【眼影属于私人闲置用品，发货后不退不换，谢谢】\n【6点前拍下当天发货】\nPERFECT DIARY 完美日记    探险家十二色眼影盘 功效：持久不掉色 规格类型：正常规格 颜色数：8色及以上 产地：中国 品牌：PERFECT DIARY/完美日记 功效：眼部修饰',21,1608205081107,0),(88,117,200,0,'纪梵希高定香榭红丝绒唇膏N37\n纪梵希口红\nN37  n37\n正品会提供购买记录',12,1608205173396,0),(89,130,120,0,'SK-II护肤精华(神仙水)化妆水，余量请看我的实拍图，请勿盗图，仅此一瓶',9,1608205319700,0),(90,130,340,0,'科颜氏金盏花植萃爽肤水 补水保湿收缩毛孔控油',9,1608205410264,1),(91,130,20,0,'肌研极润保湿化妆水，全新未拆封 \n日期到202108\n包邮到家\n偏远地区不包邮',9,1608205453538,0),(92,117,52.8,0,'圣罗兰粉饼7成新\n感兴趣的话点“聊一聊”和我私聊吧～',5,1608205602270,2),(93,130,20,0,'Flotte 花洛莉亚  Rainology雨辑  Hedone Joocyee Girlcut auburn 傲本 Perfect diary 完美日记117 口红唇釉',3,1608205675321,0),(94,130,36,0,'卡姿兰唇釉丝绒雾面哑光口红女学生款平价不掉色不沾杯豆沙色唇彩 \n卡姿兰口红唇釉 卡姿兰口红唇釉，18.9元一只，两支35元.4支以上16元，女学生不掉色防水\n保质期到 2022年\n\n本人发布的宝贝是卡姿兰天猫店仓库微瑕品  现低于成本价出售\n卡姿兰正品彩妆保证 不介意的各路仙女放心挑。低价出售 一经售出 不退不换 敬请谅解！\n可官方扫码验证\n新旧程度:拆封或试色一过一次',3,1608205751574,0),(95,130,18,0,'全新正品戈戈舞小白管唇釉丝绒雾面哑光学生款平价口红 \n\n全新正品 未拆封 戈戈舞小白管唇釉\n绒雾质感 气质显白\n创新水化雾配方\n701#焦糖胡萝卜 702#吃土奶茶 703#桃红女团\n704#泰式红梨 705#红丝绒\n706#焦糖栗子 707#烤番茄 708#蜜桃绵绵冰\n【全新正品】未拆封\n一支13.88包邮',3,1608205820292,1),(96,117,185,0,'白鸭绒连帽羽绒服外套男装上衣时尚简约百搭帅气质休闲 \n非全新 新旧程度如图所示\n尺码L175/92A\n实拍图。实物比拍的图片好看，很多深色的拍不出质感。还在架就可以直接拍，24小时内发货。\n收到货有问题随时找我，不要随意中差评哦！衣服有问题可以退换。\n家里很多闲置衣服还很新不穿又占地方，价格标得很低也图个做买卖的乐趣！赠人玫瑰，手有余香，相识是缘分，喜欢的可以关注我，不定期上新，常来逛逛哦！',35,1608205822752,2),(97,130,66,0,'黛诗澳 5支装中国风唇釉套装学生款不掉色不沾杯哑光雾面口红\n\n❤️『新店开张，亏本包邮赚人气！只卖两天！只卖两天！』\n！关注我！！关注我！！关注我！主页还有很多好宝贝哦！总有一款适合您！喜欢请直接拍下哦~！\n\n一盒是5一支哦，5支不同的颜色\n甄选5款热门显白色号，\n烂番茄，复古红，树莓紫，铁锈红，奶咖色\n都是半哑光的，超梦幻\n\n亏本冲人气   15.9一盒.包邮\n直接拍就可以了\n\n\n❤️『温馨提示』\n1、大家都忙，时间宝贵，所以就不支持上门领取了哈！\n2、收到包裹签收前一定要检查包装，没损坏再签收。\n3、点赞并且收藏一下，防止以后找不到我家哦。\n4、我们有大量新品，每天持续上架，欢迎下单！',3,1608205873894,1),(98,119,26,0,'【清货】稚优泉水光唇釉仙女棒正品保证全新未拆封日期新鲜 \n\n转手原因：退出稚优泉代理 ！现货有限，卖完下架\n\n日期都很好，大部分都是23年，极个别是22年\n春夏限定s09没货，s66没有，其他都有\n\n26，包邮，新疆西藏不发！',3,1608205961947,2),(99,114,680,0,'iPhone7 \n换过电池，机身有磕碰，介意勿扰\n价格可小刀',25,1608205969479,2),(100,119,36,0,'unny持久唇釉花园口红 功效：持久不掉色 功效：提升气色 功效：持久保湿 产地：韩国 功效：持久 功效：自然\n\n\n全新 全新 全新  全新转让',3,1608206022441,2),(101,119,22,0,'完美日记 天鹅绒唇釉mini装（色号 V01 V02 V07 V08） \n全新、未拆封、保证正品\n这里付款后还需要自己去官网公众号下单再次付款（11+1）\n下单前请咨询一下是否有货',3,1608206119355,1),(102,131,15,0,'全新完美日记至臻柔色丝绒唇釉V01、V09，2个45包邮，单只20不包邮，保质期2023年9月\n全新沁色微光水唇釉H11，15不包邮，保质期2023年3月\n全新雾色梦境哑光唇釉708，15不包邮，保质期2023年3月\n全新倾色暮光唇釉116，20不包邮，保质期2023年3月\n全部出的都是全新塑封未拆的，首图拍照的是我自留的一套，均在手臂试色都是24一个包邮\n想两套一起拿走价格私聊',3,1608206262520,1),(103,114,36,0,'休闲工装长裤潮牌纯色直筒哈伦裤子男秋春季百搭宽松阔腿九分裤潮  \n\n【亏本冲销，少量现货，速度下单！】\n\n【成色】：全新，含吊牌\n【面料】: 纯棉\n【颜色款式】:黑色/军绿色/卡其色\n【尺码选择】: S~5XL   \n\n见最后一张详情图选择尺码，不知道穿什么码数的可以咨询客服小姐姐哦~\n\n裤子休闲时尚舒适，做工精细，设计周到，还配有腰带，尺码齐全，欢迎选购！',44,1608206285295,0),(104,131,56,0,'花知晓 Flower Knows 口红唇釉眼影高光眉笔 猫宝 宝 水母流星 星象仪 女巫集会 金鱼花火 泰迪熊 独角兽 白夜黑昼\n理东西的时候发现这么这一年里花知晓买了这么多，太多了实在用不完！！！挺多都绝版了\n基本上都是仅试色！！！\n最早一个买的是一年前，所以全部都至少两年保质期！！！\n太多了懒得标价了，圈图私聊问价，都是半价左右哦！！！\n多买多送！！！',3,1608206310594,2),(105,131,55,0,'韩国UNNY镜面水光唇釉唇蜜珠光持久烂番茄玻璃唇…\n颜色分类:105#玫瑰豆沙',3,1608206376959,1),(106,131,46,0,'稚优泉雾面哑光唇釉持久保湿唇彩蜜染唇液滋润口红豆沙女学 \n全新正品，假一陪十\n需要可以私聊哦，\n一只24元\n2只45元\n除了新疆，西藏，其它地区都包邮，\n欢迎私聊',3,1608206427237,0),(107,131,160,0,'北面 羽绒服 粉色  \n去年买的 穿过几次 八新 羽绒服。\n有两处小瑕疵，正面被猫爪子勾了两下，袖口下方被刷子刷磨毛了一点。',35,1608206523763,2),(108,119,200,0,'全新出口欧洲尾单正品小鸟男款半大羽绒服，重磅桃皮绒布料，90灰鸭绒200g，全新手工测量难免有误差介意慎拍。出口欧洲尾单，码大量好尺码再拍不退换。',35,1608206735855,1),(110,131,800,0,'黑色丝绒亮片羽绒服\n全新，吊牌齐全，质感特别好.丝绒面料上面加亮片刺绣，很重工，不是那种便宜货，高端品牌 小码，版型宽松，体重130斤以内都可以穿，识货的来',35,1608206985493,1),(111,131,99,0,'以纯羽绒服，实体店买的，宽松休闲款的，实体店8.5折下来560买的，狐狸毛领，不跑绒，9新，喜欢的来',35,1608207068042,1),(112,132,199,0,'小度在家，本地交易\n感兴趣的话点“我想要”和我私聊吧～',31,1608208524638,0),(113,133,28,0,'VTCICA防晒霜清爽不油腻\nvt老虎防积雪草晒霜\n感兴趣的话点“我想要”和我私聊吧～',4,1608250849945,0),(114,133,37,0,'旗舰店购买，有订单图【防晒大白盾】EltaMD安妍科防晒霜女面部 防紫外线隔离SPF45 48g',4,1608250896623,1),(115,133,16,0,'ynm防晒霜学生美白补水防水防汗防晒霜防紫外线spf50+防 晒隔离霜\n  全新正品！一支也包邮！限量8支，售完就没了\n本交易仅支持邮寄',4,1608250965023,0),(116,133,28,0,'伊贝诗防晒霜女面部学生军训防紫外线隔离霜清爽不油腻美白spf50可以当隔离使用很清爽不油腻适合任何肤质  九成新',4,1608251005017,2),(117,133,49,0,'薇姿防晒霜 \n旗舰店购入，有两个',4,1608251046907,0),(118,133,30,0,'【全新塑封】正品RAPERN 娜盼 美白防晒霜SPF50  PA \n\n有效期至2023.01月\n一瓶包邮\n高倍防晒，颜值在线，韩国原装进口\n发货有包装\n\n  适用部位：全身 功效：美白 防晒指数：SPF50+ 化妆品净含量：50g/mL PA值：PA+++ 防晒分类：防晒乳/霜',4,1608251094762,1),(119,133,26,0,'高倍防晒，颜值在线，韩国原装进口\n发货有包装\n\n  适用部位：全身 功效：美白 防晒指数：SPF50+ 化妆品净含量：50g/mL PA值：PA+++ 防晒分类：防晒乳/霜',4,1608251156007,0),(120,131,60,0,'安耐晒脸部防晒霜金管金色软管  SPF50+\n还剩一半',4,1608251225613,2),(121,131,35,0,'croxx肌蜜滤镜轻粉饼定妆持久保湿遮瑕控油\nY51浅肤色\n就用了一两次，一直在闲置\n粉扑我觉得太难用给扔了，无包装，贴小镜子的膜还在\n觉得它占地方所以出了，欢迎私聊',5,1608251309189,2),(122,131,10,0,'韩国咬唇妆防水自动唇线笔不沾杯唇膏口红笔唇笔豆沙色姨妈色包邮\n感兴趣的话点“我想要”和我私聊吧～',2,1608251425674,0),(123,131,25,0,'Nars正品卷笔刀唇笔刀\n自用保真 实物如图 所见即所得 不包邮不退不换不议价 谢谢',2,1608251461987,1),(124,131,17,0,'ators口红笔唇膏！全新 全新 全新 全新 全新  \n超级顺滑的唇膏笔！！！共10支呦！！！\n1支17呦！！\n5支80呦！！\n10支150呦！！\n可对比淘宝价格！！！\n保真！！\nP：因为价格非常非常划算，不支持退换货呦！',2,1608251507108,1),(125,130,29,0,'Judydoll橘朵弹柔绵绵“小花管”唇膏，#17肉桂豆沙柔雾。用过两次，我黄黑皮不太适合…想看起来温柔点儿结果看起来很没气色…枯了…',2,1608251576378,1),(126,130,18,0,'按压口红和唇线笔打包出 小众的应该不是啥牌子 去哈尔滨商场里一楼搞活动买的 颜色很好看 东西太多了 没地方放 喜欢的直接拍下就可以 没怎么用过 余量都很多 包邮',2,1608251604698,2),(127,130,12.8,0,'戈戈舞小橙笔口红唇笔小众品牌学生款唇膏笔奶油杏色 \n\n全新正品  未拆封 戈戈舞粉漾绒雾唇膏\n\n绒雾6色 \n201#雾霾桃桃色 202#栗子枣糕色\n203#纯纯女团色 204#裸奶油杏色 \n205#莫吉托红莓 206#焦糖红梨色\n【全新正品】未拆封\n一只12.8包邮',2,1608251663757,0),(128,130,40,0,'CINEMA SECRETS剧院的秘密好莱坞的秘密\n唇釉 镜面哑光 LG110  apple box\n全新未拆封  颜色不适合我\n40包邮不议价',2,1608251711298,0),(129,119,50,0,'玫琳凯唇膏笔 摩卡豆沙色 全新 转给需要的亲\n感兴趣的话点“我想要”和我私聊吧～',2,1608251769032,0),(130,119,16,0,'戈戈舞口红笔 16一只包邮 两只30包邮 不议价 确定要再拍 非诚勿扰',2,1608251811159,0),(131,119,258,0,'TNF北面羽绒服白鸭绒棉衣棉服外套拼接刺绣1996情侣款冬装\nQ1996北面羽绒服\n绣花，三标一致100%纯白鸭绒)\n黑色  白色 橙色 粉色\nM-3XL',35,1608252068153,0),(132,119,120,0,'超值回馈 3色硕大貉子毛领可脱卸全棉女款连帽中长…\n颜色分类白色,尺码M',35,1608252144338,0),(133,133,60,0,'棉衣男短款加厚棉服冬季韩版潮流ins学生帅气棉袄面包服pu皮外套',35,1608252277457,0),(134,133,280,0,'闲置gxg全新羽绒服M码和XL码，\n感兴趣的话点“我想要”和我私聊吧～',35,1608252322296,0),(135,133,232,0,'GXG男羽绒服，长款羽绒很厚实。专柜正品。9.5新只穿了一次 感兴趣的话给我留言吧！',35,1608252381539,2),(136,133,48,0,'粉底液干皮救星持久1co 1c1遮瑕保湿持久粉底液不脱妆 3 0ml正装全新\n（不是小样）\n颜色：1co象牙白  1c1自然色\n      一瓶送美妆蛋一个\n姐妹要哪个颜色请点击我想要留言喔',6,1608252560380,1),(137,133,124,0,'大师粉底液 蓝标粉底液30ml有两个色号2号跟3号 全新正品  \n为了未来之星的健康着想，停掉所有化妆品，把之前囤的一些货趁日期还新鲜便宜转出，全新，正品保证，要的可以直接拍下或者私聊我！',6,1608252624918,0),(138,133,28,0,'日本 redearth红\\地球粉底液30ml \n只有F400     几瓶，自用的\n实体店屈臣氏买的\n全新包邮！支持鉴定！\n不正包退！\n不正包退！',6,1608252654658,1),(139,133,50,0,'出一个柳丝木自然色粉底液，全新未用，想要的私聊我呦。薇娅推荐柳丝木小方管粉底液新款养肤控油遮瑕保湿持久',6,1608252701579,0),(141,133,37,0,'韩国Secret Age秘密时代粉底液第三代无尘…\n感兴趣的话点“我想要”和我私聊吧～',6,1608252914747,2),(142,131,48,0,'品牌型号：欧莱雅吸油棒粉底液小样5ml（四支）\n新旧程度：买正装送的，正装也出，小样也出\n转手原因：油皮适用，本人干皮，不适合\n入手渠道：淘宝官方旗舰店购入\n4支打包出，小样色号102，保质期20230601',6,1608253000578,0),(143,131,360,0,'美宝莲旗舰店轻雾唇釉哑光丝绒雾感口红持久不易掉\n还有三只\n28一只包邮常规地区出',3,1608253150643,0),(144,131,66,0,'安娜苏许愿精灵唇釉，幻境灵雀水润唇釉笔 香港崇光百货购入，日期有一段时间了，所以，特价出，没用过几次，余量自辨\n色号400\n江浙沪包邮，其他一半',3,1608253234503,2),(145,131,30,0,'卡姿兰（Carslan）幻境水光唇釉 (镜面滋润保湿不易沾杯不易掉色 唇彩唇蜜口红)S03#漂浮萤火虫4g 全新，未拆封',2,1608253307918,2),(146,131,500,0,'colourpop唇膏笔 颜色如图\n代购入，自用保真 余量90\n一只38，三只打包100',2,1608253384543,0),(148,130,99,0,'神仙钻石高光修容盘脸部提亮闪粉土豆泥一体盘腮红鼻影哑光三合一',7,1608253654658,0),(149,130,66,0,'Judydoll橘朵哑光 小花管唇釉\n #85蛮熟石榴  \n#86南瓜烧酒\n一支34\n两支60元',7,1608253703298,0),(156,136,69,0,'你好',2,1608274425943,0);

/*Table structure for table `commodity_img` */

DROP TABLE IF EXISTS `commodity_img`;

CREATE TABLE `commodity_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8mb4;

/*Data for the table `commodity_img` */

insert  into `commodity_img`(`id`,`commodity_id`,`img`) values (75,63,'FsR5SRkLy_eu_D6SoEDKQuqGrAHO'),(76,63,'FgZeRzYKCmAKEwUR6tPXUgPOlc-R'),(77,63,'Fi999UhwlsMYbwXv47XZuorD7250'),(81,66,'FvYxTH9pZJ77w57e154nvG9UEn_3'),(82,67,'FoMtn_9zj9tnxxjexkI8_MqFl0NH'),(83,68,'FmlvZVrQ803Okkd0PA8Ir9cZbsbB'),(84,69,'FmVH_jn4yop8Mp1t0MvvAWdCf0Je'),(85,70,'FkpF2ZrXok1In28AbbOYI94Hk-Tt'),(86,71,'FjVyi4BAwwhwfxd1A9PlKzJOrEly'),(87,71,'Fp4d10hbEbS60_KbPs2gHLYSURiH'),(88,71,'FvrYv-Y2qdotUkfCbL7peEj4ZB0D'),(89,71,'FouyFJNkJ3yjF5Ha7zZJGKgfB6Lp'),(90,72,'FkABqCucXd76WGeaDO1emqJ9QJpG'),(91,72,'FpWvPw4b9dJte8USzf33ICvwdHI3'),(92,73,'FgEAXRRD4d8u4orLbQZqjOsy_fTM'),(93,73,'FnaAIaZ3wGFaUl7ylhErhhzxSgE4'),(94,73,'FtfaxaMPXkt59J96dDeZFM4b66ZN'),(95,73,'FtIu1ClpMaQ_MrF2CNw1-xas5-Ay'),(96,74,'Fih5rDMrOvR47qTdtLpJ2x4Mkn1Q'),(97,74,'FiDT6eoolBrQU3b7sbUFBObjpY4L'),(98,74,'FjOhiofztkax-ssr_Z9rBkIcdJjD'),(99,75,'Fhbm5pEPzMyHdN1hNHXlPoEHrsW8'),(100,75,'FnhIkk75byXhqvrxPda5EISS8Nkk'),(101,76,'FvyOK6-2f3o31VtcgXQIMMT-qUVE'),(102,76,'FmfGPf7-Qq-XBfsxhoqXzyPcGLn_'),(103,77,'Fu6oijwmptjJJxMtL6V03Gozneg6'),(104,77,'FryTBenYfYbK2K0c4F0QvSKy6mdC'),(105,78,'Ft3zH5LIIgYxkhDndKNaegKYVF9M'),(106,78,'FsHDR5okAHZJETPQeV6qxMYoP_Oj'),(107,79,'Fit8-YteAzCEQv479LPoGyDloki8'),(108,79,'FschzWfcOVLkHTh-M68JOi3NlHSE'),(109,79,'FvBDJHMBsv_Rj5cnr3knhhv0EgP2'),(110,80,'FjqdvDg_1zbxBNp1bnLruwOtZu92'),(111,81,'FuQDA3y_xY4RRdFNP2vdGQgpjV7K'),(112,81,'Frd7dqIPmxI5UxH7WtLZ-2lOsa5_'),(113,82,'FouyFJNkJ3yjF5Ha7zZJGKgfB6Lp'),(114,83,'FouyFJNkJ3yjF5Ha7zZJGKgfB6Lp'),(115,84,'FvubcfQt5e4Fllqb67noVSgqIV86'),(116,84,'FnOiLsuKubcVqSGMjFVPsATfjkwW'),(117,84,'FiRTcPe-3ODORXsOMAJWWNxlFCrh'),(118,84,'FiNeKxmcE01Q91UX193Ph-bZIaIa'),(119,85,'FlAQ7H63ZAoyzCfygGRspSrMD1gH'),(120,85,'FqwMaeUtlhrq3TgmPAIi8n-iKV-y'),(121,85,'Fpy07DD0mDtcJsFRVQqHfExn5LC8'),(122,85,'FohoY6OXspKJ7JBkGeSafqXs3e3r'),(123,86,'FqIGuT05EsVyRjujRHI9YNpgFGIZ'),(124,86,'FtbnQZl840NC8Go3Zh7hmwpmwrKo'),(125,87,'FtY5syrCH7hFmfmii3224JlNZZrj'),(126,87,'Fmqi-RRmX-GQfJ9SAQbTbjLfUm2d'),(127,87,'Fs68JxH-b86rbOC-wdC9GVcuasjp'),(128,87,'Fmk5V_P5hEZWho3V0QCWw-dcohBG'),(129,87,'FvV1g4lu_vC_K3m6RL-NkMN3er0O'),(130,88,'FlrY27S5ZJ1cMKNdbClbzBIVQJs4'),(131,89,'Fhi08QWT-2z2V-1tI6sbYbynIr8I'),(132,89,'FovkQnciOI3wlTcWiS7TVq6G-yMI'),(133,89,'FgjyqhYeVI5SqiUtZcVvMX9Ut8M7'),(134,90,'Fm91l6qiaf0FJHQozFK5FVmpOgZZ'),(135,90,'Fv4h1ADW7BQme4z4QKv218aSY_7g'),(136,90,'Fv5-NqtoDTmfb2HemvJXmIVyZW5S'),(137,91,'FltFxNH3drn0kKX0Jq4CS5q5e7MO'),(138,91,'FqiSrDDaV9QNGAI9vK-GCd-L0ioY'),(139,91,'FlrkmayqlGnjUEnfKnHyYOjMUP6c'),(140,91,'Fhuqlk1tDFfhl-MnPyjfoF9qqJ__'),(141,92,'FgHQjvKLYFSBnh9ppHO_2Twd2KUX'),(142,93,'FsokecEtIWHHCpKmX-r9K4yKUOaw'),(143,93,'FpLMNoepjjxtKfasqD2r6U4hzSoG'),(144,93,'FtUzZ6zbL_GAgFMvyE0s-45RCv5F'),(145,93,'FmIYRwrAnWc2Y-2ueNcmo8LjOpfu'),(146,94,'Fq4bXDDSuQjRmnrmd_kAxneFHjA7'),(147,94,'FsnP7oU0gaAUgSKAEQa10uPJc2gK'),(148,94,'Fp35no2_S0yXv6GMg65Rqk-X3NVu'),(149,94,'FhSPdRc69cghoIq7HbB6yi8wmXsQ'),(150,94,'Fno00JHNvjocEag2-r8Yc5t4KT1w'),(151,95,'FvOO7zKwQWP6lUz0BX9epVPgZHz2'),(152,95,'FkSEU2mriMou0rShp5GnmEdHEXMF'),(153,95,'Fr1kF9rHHNuXlkjDUmd9dxFkutS4'),(154,96,'FnURH916wjZp3VJIj1i1pgplhwwr'),(155,96,'Fl0dCNpX4zHFkH7n-MKtW1lRxT4Q'),(156,96,'Fh0aiij86-J6UJCZfUlz5ibAyflD'),(157,96,'FkXQuEw53K8vQIajnC0gLT6bTf5u'),(158,96,'FtKLZH0b-AGKbfDuagvlwEA9zS4x'),(159,96,'FivYqIaOdTE4CWUBHP6OKG_rF8CA'),(160,97,'Fi_CULWtxiC_fOY6gwU3cr3djiTK'),(161,97,'FmXl75qAQ2Uw6kEMmJtErDzQ8DKu'),(162,97,'FrROZUMMUXagZ5AMnYh-J5eK-l00'),(163,98,'Fmw2sBsC5EqJ5wU_J-1OiaC5MZgj'),(164,98,'Fgo37XVDX2terwTOHorpUJR9yKfQ'),(165,98,'FjD3x2Q0mw7oOT7Z3oHv8VmOjlUl'),(166,98,'FoUBrKGdOt93zM7SUfRm24B3O4xJ'),(167,99,'FtPZjzkueNcm6RPpw3SwNfEb-Ajk'),(168,99,'FiAB8TOYJG7BjEmJWuYAEz1SFz5E'),(169,99,'Foz57WoTimf8e0GG0x3A6Jh5QwiV'),(170,100,'FkvNPNsf3k7w7lgS6lbh0E-Il7BS'),(171,100,'FuMzxn8afespRuLVYFtSBQQK5qWt'),(172,100,'Fhgi2CRqsy_pItWazWYEKPzZw-K8'),(173,101,'FsbqEY6TPyBB3NYQ_fW5E6QmzBd3'),(174,101,'Fp_i0yhsi0N0KXfTwwEJjBtzOxiW'),(175,101,'FkCNmAxbcdU07hqRwGjYnvIOV96t'),(176,102,'FkUNPzETuAy8IR4nDCwiT2H_KlYj'),(177,102,'FtsNFs318zzxohP6pnWx0CfSCcRG'),(178,102,'FrHFjKbwrCziAt8xZUv0h1kfc97b'),(179,103,'FpTJP5b6zhDKKmQYCCGIYIlzUdky'),(180,103,'Fp7oHyyHkZ09yupbt_n1oEUSTSD0'),(181,103,'Ft4vtRwY4zS22plHfOlbecqeV5Ev'),(182,103,'FlPwpmnIJav68FQERCpj_85gWzGU'),(183,103,'Fll6Dm-wfCdTOfvwOyHABkRovKAk'),(184,104,'FqyFzQR_rJK0hw2Su9i9Pz5NrNVe'),(185,104,'Fib5fH6n_qP47a9NrL87P9-j0-vm'),(186,104,'Fr0d4Jp6MjEVXseeag5Xv0vbsSjx'),(187,105,'Fj5wthhSyQyGJOfsw29FAx90g5UM'),(188,106,'FuHWkGvZ7V1AkzuMuDDMxIinilqD'),(189,107,'FiuSbGoAB1RGsFLhjjxA1JQxpd63'),(190,108,'FqLikj_4Xrjmap6e2FIPITMoMSgR'),(191,108,'FrNYh6xDyrVhE2xZpXm3K48At6kz'),(192,108,'FhFmM4lsv7v8Pd0hIcxGvtp4AW-D'),(193,109,'Fsg93a3mdc18bH11cM_cBsOKyR11'),(194,109,'FlNIxppdGGoVUh7vk5qH3fbPjhfu'),(195,109,'FhJQNmx4tpXCdnlbbana0vhMYVhW'),(196,110,'FkFeEDwTEC6uHpJRQ5mbWdy_lSf8'),(197,111,'Fsgjklg6tr8_JVzdH-H7t_Emvc7L'),(198,112,'FgxNPLd21nMLpsRjkT2Sc7YiO2BI'),(199,112,'FkDGVZ89xI2RrkwTHD2vMUr3noQ3'),(200,113,'Fi1YPzUCWx95ebbWOCG-AvweSO7_'),(201,113,'FsaJuodLZAy3nR_gjHXHZEqIl-72'),(202,113,'Fm60Dm4SS75eAj7bcNG2XKEIY2dD'),(203,114,'FrO02xJVwYf7HegnAfmGxpMD5kBT'),(204,114,'Fm8iuVKssFmn_uVV2LY2v7UL998K'),(205,115,'Fko1hgvpwSKLEouFUNjtt6UptvYJ'),(206,115,'Fnkrmj3lfaok-CYOw--hyo0OQyYG'),(207,116,'Fs83oqWjKQw4o9Aqc9uMHIgJom-g'),(208,117,'FsdgpPlZ2uF2DTIYX4DivHSfG8Mq'),(209,117,'Ft5jYQj13QeDYGCjDEoflmxVlRYH'),(210,118,'FhKid392FIOcLZ2CEQ1tGYv1zJcY'),(211,118,'Fi_3atEvnu3mOg-WlvaOtUdO93Pe'),(212,119,'FmezOojqQnTYDUzB-6PDn4hX4mFh'),(213,119,'FthN1j7gE9sjk7JO8JmPKwwAs8jd'),(214,120,'FgZcX-9pz9UHOJp5ana9FWmmMc1j'),(215,120,'FgLBRYJtHfP4SuALRAns6WyhN-MW'),(216,121,'FhrlhSBjGDR95RjeDOh_BoPGPrQY'),(217,121,'FhVf31BKTKTa7Dha40i1FsijYtXn'),(218,122,'FjDK5le1QUr3B484CYiEzfG9rpf3'),(219,123,'FosBsywPO-lHBTi0-9BoGmil9nOe'),(220,124,'Fue572e_6eHdD_TkhYethalJnuBO'),(221,125,'FhEjDDx_-qcYVDp1ea2iht0OPA-o'),(222,125,'FnqLkrLyBCWmfHxi3k5QwXl1Pg4_'),(223,126,'FgbMm_67ZaIcc7SbhgeP8tWqkrmi'),(224,127,'Fmme8kMiGDiWsgrXC4CjfvCk-bua'),(225,128,'Fmv3QA2gcgsJYJnA41EmUI-CoJzc'),(226,128,'FjqVrVdDSAmSAcagU9TvlJ_yPWSn'),(227,129,'FthZsZrkPyO1KzaOAsYlpTeO7YPQ'),(228,130,'Fn5uA1irAUV0chpANtRihzZEaDjK'),(229,130,'Fgb3HGGnKnaYeo9AtyYaieanVNCB'),(230,131,'FruYX-wxPhXppVhpJeDdt8yyASSp'),(231,132,'Fia7pZhKDiRUfOIf6gmZAiQ3iWfi'),(232,133,'FgWLAcHc5rEdt-csEj8JBIhPz_qS'),(233,133,'FijdVN0-n4dis5XMpDDW-bJX9n1I'),(234,134,'FvDmAofW16rAc8jJLUseAX_7vUm-'),(235,134,'Fhzi358Q0XZQwmzBP8YV4yAJah_T'),(236,135,'Fmk7Hu6DJE9tdKVupn2Xxy4BNvrE'),(237,135,'FoDIN8QG9ROOPwOUTtPzB5P0hFVC'),(238,136,'FgxHkquAKHkbsDc9yHt92uPUZQ4Q'),(239,137,'FpgsjMS8dhf8mH_Fbc2jbh4lo2KJ'),(240,137,'FtHYzC3moie3wXQs8toCPTq_-qDJ'),(241,138,'Fg-CAAmV9jEJOueniBYfohLnUUoJ'),(242,139,'FjA9Z2QuPEy69ncr0tdSJ7J6xlxu'),(243,140,'Fjm2KDr7vL_S0LIstupbG0aTYEUb'),(244,141,'Fg6EqlrS2n9e1ADgo5w3p0BYf8T4'),(245,142,'FvRY-pvX0f4L1nFJdve3aIuiJEgu'),(246,143,'Fqr-RYeyhhHzAtdg9CDQrOs_LBXk'),(247,144,'Foocm8hg0M5x9GOAdLEldbtke_XO'),(248,145,'FpB9VTh3B3Ofa6zshwVtTMdCXJAl'),(249,146,'FjqFTECc8jL6jEa95d7ULW-luByE'),(250,147,'Fm1jf6Urqnt6RBOI2n5loedV6i4I'),(251,148,'Fm8Kx5m8DPCVT0OwlWr3nAnBmVqo'),(252,149,'FgS_SeRbVz-xXW8dOUok_LnQ1BpG'),(253,150,'FgS_SeRbVz-xXW8dOUok_LnQ1BpG'),(254,151,'FosBsywPO-lHBTi0-9BoGmil9nOe'),(255,152,'Fjm2KDr7vL_S0LIstupbG0aTYEUb'),(256,153,'Fm8Kx5m8DPCVT0OwlWr3nAnBmVqo'),(257,154,'Fm8Kx5m8DPCVT0OwlWr3nAnBmVqo'),(258,155,'FgS_SeRbVz-xXW8dOUok_LnQ1BpG'),(259,156,'FgS_SeRbVz-xXW8dOUok_LnQ1BpG');

/*Table structure for table `commodity_type` */

DROP TABLE IF EXISTS `commodity_type`;

CREATE TABLE `commodity_type` (
  `type_id` int(10) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(10) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type_img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4;

/*Data for the table `commodity_type` */

insert  into `commodity_type`(`type_id`,`type_name`,`parent_id`,`type_img`) values (1,'美妆护肤',NULL,NULL),(2,'唇笔',1,'FoqKq0xUsKOFWNAqJRDJAAZIbK2O'),(3,'唇釉',1,'FvFGY-i_5TKeCHL4j6lgLI9h5xSz'),(4,'防晒',1,'FttIQO1gRGSgDfdJI_Ih43YQJ3NV'),(5,'粉饼',1,'FtKdqpr4flzZ5-kprAoBpZeDZedc'),(6,'粉底',1,'FonEF7-9wDv3OBV3hHTfens047pj'),(7,'高光',1,'Fi6j4ICNX89zSFSD513zjZTVvJD6'),(8,'隔离',1,'Fjhvlamy3oz3diIXV3SuR5zaL7Xn'),(9,'化妆水',1,'Fj52DI5-x5coRaqTHHjlB7ZPLQCe'),(10,'洁面',1,'FkOU75dnQ67_uO1_yxYNq3Zqz9Bm'),(11,'精华',1,'FtsFGZyP93rOtwfWYME6C5wqcbed'),(12,'口红',1,'FovwUN3JU5E4YCFJ1Oz82bwul8k9'),(13,'眉笔/眉粉',1,'FlFFOlA_-mRoGRnlLfgEQAidBnFv'),(14,'面膜',1,'FpZUzMKiIuzmh5jbEk-oGhAZuyjS'),(15,'气垫',1,'Fm9VdSJKyMTXV8yzlI6Id28Q311_'),(16,'腮红',1,'FgSCGxgwghS-dtdmWBGyEOvJ42IV'),(17,'散粉',1,'FgjIFDwdO_2M08bYoWeek155Nlcj'),(18,'卸妆水',1,'Foy0zvY2_yXbMYFV1GCa2U1wNuEJ'),(19,'眼膜',1,'FlwRW-2PLVrhhVnCLH_jzYkzi7GF'),(20,'眼霜',1,'FmB0EjjGYy3GilO91YoU4_SX9IK_'),(21,'眼影',1,'Fn3f_ydFrYBGibupdUX1CW9kkoSa'),(22,'遮瑕',1,'Fv9AtCmNsLMcbzPtcHCKn4buK5yn'),(23,'香水',1,'FsWhIgrNKjbox-bzpKRWAxevPiBZ'),(24,'手机',NULL,NULL),(25,'苹果手机',24,'Fvpv1YPLnUxpDpN-a4wxiLMozDTq'),(26,'华为手机',24,'Fr1Bp151XdPDCxgZfpWdZSAydJJb'),(27,'荣耀手机',24,'FoUVndpaS2U4qnmOiSSRDkDh7Ntw'),(28,'小米手机',24,'FlObpvuWN1pbAYwzQ54VO76CYBFs'),(29,'一加手机',24,'FvMB_sogSywPGwDiT8lFCiaadgL_'),(30,'红米手机',24,'FpX0DEoEDLHvmvDdtsPWz6P8YJ5h'),(31,'三星手机',24,'FhHNAQtBWzXQieDOuwjHjkjr5BnC'),(32,'vivo',24,'FqNZkyvClSkzj8BEHKWFmMN5_RAY'),(33,'OPPO',24,'FtdvMEpwkp4jmHHCgMX0E5RxL1fx'),(34,'男装',NULL,NULL),(35,'羽绒服',34,'FjhAlAqyaxmCKxEfDF0yh3SpetP8'),(36,'棉服',34,'Fg6Z08wotwcaJkb88nSSkwkSW9EP'),(37,'针织衫',34,'FoCgeasMPnT3lyaVL70aTFG7ye5C'),(38,'毛呢大衣',34,'FrZpwuUjBWRK_y6awgnNqdKVbvIT'),(39,'皮衣',34,'FmUAOaPwCEWAeCQztVfTrEEziaLJ'),(40,'风衣',34,'FnZX9sLUdFqKPrcW46zKo9N5HVuD'),(41,'衬衫',34,'FhTQsdy8anctMI4JDQxQfpYVeAZ2'),(42,'卫衣',34,'Ful5N62KznPPpYoB4w9jFUvZQdSA'),(43,'夹克',34,'Fl5flmzTitCxuxCOqaUCc0fiKtHw'),(44,'工装裤',34,'FtQWDemis7_XtzLv-TIDjQWindYM'),(45,'牛仔裤',34,'FshF0vNifaIy43e-0I2JzrV0U9qX'),(46,'西裤',34,'Fv7-MfwaY_donq6w-6WpvsG_lwUx'),(47,'电脑',NULL,NULL),(48,'家居',NULL,NULL),(49,'母婴童装',NULL,NULL),(50,'女装',NULL,NULL),(51,'家电',NULL,NULL),(52,'医药保健',NULL,NULL),(53,'数码',NULL,NULL),(54,'玩具乐器',NULL,NULL),(55,'钟表珠宝',NULL,NULL),(56,'箱包',NULL,NULL),(57,'运动户外',NULL,NULL),(58,'图书',NULL,NULL);

/*Table structure for table `conversation` */

DROP TABLE IF EXISTS `conversation`;

CREATE TABLE `conversation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `touserid` varbinary(20) NOT NULL,
  `last_mes` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `conversation` */

/*Table structure for table `mer_image` */

DROP TABLE IF EXISTS `mer_image`;

CREATE TABLE `mer_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(50) NOT NULL,
  `mer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `mer_image` */

/*Table structure for table `merchandise` */

DROP TABLE IF EXISTS `merchandise`;

CREATE TABLE `merchandise` (
  `mer_id` int(10) NOT NULL AUTO_INCREMENT,
  `mer_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `mer_desc` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `mer_price` int(50) NOT NULL,
  `mer_type` int(11) NOT NULL,
  PRIMARY KEY (`mer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `merchandise` */

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_mes` varchar(500) NOT NULL,
  `view_num` int(11) DEFAULT '0',
  `like_num` int(11) DEFAULT '0',
  `post_date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4;

/*Data for the table `post` */

insert  into `post`(`post_id`,`user_id`,`post_mes`,`view_num`,`like_num`,`post_date`) values (41,114,'达尔优MINI版，小巧方便。\n 手感不错，支持官方驱动和宏，\n成色如图 有黑白两色。五色变光，办公，游戏必备。   \n鼠标都是自己动手改的， 与网吧直接淘汰的不同，本鼠标换了\n全新欧姆龙机械寿命两千万次的微动， \n全新USB线， ^_^\n全新脚贴。 \n本鼠标全部进行了深度清理。   \n发货之前全部酒精消毒，七天之内有质量问题包退换， 保修三个月。',20,3,'2020年12月17日 16:36'),(44,127,'十成新，只戴了一分钟\n感觉颜色不符合我\n感兴趣的话点“我想要”\n和我私聊吧～',26,2,'2020年12月17日 18:43'),(45,122,'出机器键盘，买了也没多时间，现在也没时间玩，以后估计也没时间完了，不想埋没了他优秀的性能，有意向的可以联系我欧',10,1,'2020年12月17日 18:45'),(46,114,'很多款手表哦 \n英叔护体 \n保佑我快点把表出去\n有喜欢的可以来我主页\n 私聊我也可以 绝对价格实惠。',2,1,'2020年12月17日 20:03'),(47,132,'使用正常，没有问题，不想浪费时间，请直拍.是学生没法及时回消息 可刀 \n单耳能用三个小时多吧   \n80包邮非偏远\nEdifier 漫步者 W2 耳机类别：真无线耳机 佩戴方式：入耳式 品牌：Edifier/漫步者 防水性能：IPX4 是否单双耳：双边立体声 功能：通话功能 声道：2',2,1,'2020年12月17日 20:12'),(49,114,'牧马人一代g60\n自用没有暗病\n鼠标多用的时间不长\n手掌处有磨损。\n售出不退，离的远的不包邮。',5,1,'2020年12月18日 08:32'),(50,127,'苹果7。32 G.\n想换新手机了，充电很快。\n电池效率98%没有基带。\n没有摄像头ID可退出很好用。\n可小刀。大刀勿来！',2,1,'2020年12月18日 08:34'),(51,135,'达尔优机械键盘，本人不玩游戏出于好奇买的，键盘哒哒哒不适合在办公室使用，用过几次就落灰了唯一的缺点就是这个微软按键不能使用了(如图)。东莞市大朗镇或松山湖自提。(键盘太重不适合快递，不出外地、不出外地、不出外地)',3,1,'2020年12月18日 08:48'),(52,135,'达尔优机械键盘，本人不玩游戏出于好奇买的，键盘哒哒哒不适合在办公室使用，用过几次就落灰了唯一的缺点就是这个微软按键不能使用了(如图)。东莞市大朗镇或松山湖自提。(键盘太重不适合快递，不出外地、不出外地、不出外地)',1,1,'2020年12月18日 08:55'),(53,114,'mono贝斯包，sleeve款\n自用，换包了所以闲置，各款常见型号贝斯都能放，防护作用、颜值全都ok。\n可同城自取，可包邮。',8,2,'2020年12月18日 08:57'),(54,127,'原价2600的齐博林(zeppelin)手表德国原装进口防水真皮皮带商务休闲石英男士三眼手表 银盘棕带7680-1 1100卖齐博林德国进口复古手表 表带材质：真皮 机芯类型：石英表 厚度：11mm 机芯类型：石英表 是否商场同款：是 风格：经典复古 防水深度：50m 直径：41mm齐博林德国进口复古手表 表带材质：真皮 机芯类型：石英表 厚度：11mm 机芯类型：石英表 是否商场同款：是 风格：经典复古 防水深度：50m 直径：41mm 拍下不退哦',24,2,'2020年12月18日 09:00'),(57,136,'大家好呀。',0,0,'2020年12月18日 14:25'),(58,136,'你好',0,0,'2020年12月18日 14:54');

/*Table structure for table `post_image` */

DROP TABLE IF EXISTS `post_image`;

CREATE TABLE `post_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `img` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `post_image_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4;

/*Data for the table `post_image` */

insert  into `post_image`(`id`,`post_id`,`img`) values (71,41,'FsR5SRkLy_eu_D6SoEDKQuqGrAHO'),(72,41,'FgZeRzYKCmAKEwUR6tPXUgPOlc-R'),(73,41,'Fi999UhwlsMYbwXv47XZuorD7250'),(76,44,'Ft3zH5LIIgYxkhDndKNaegKYVF9M'),(77,44,'FsHDR5okAHZJETPQeV6qxMYoP_Oj'),(78,45,'FsuqBlG-Z4QD1ObDAhZWdIVIDI1N'),(79,46,'FtLTNJU6LPajhE_IL-V19GnkBo3i'),(80,46,'FiOvmEo9h259Z4w6NMkbblRzUbBw'),(81,46,'FuEjNVOysCGncVNFwq8HmcJlsB76'),(82,46,'FnBmMzIjI4slgVqJd2xTk_nPzj6v'),(83,46,'FvCxVnGN1IV7nitceGtSV0uF9TwS'),(84,47,'FuLAMzaqIOhcy3jiEkDyn9u6-WwR'),(85,47,'FsJwUN9cTQ_T9lL9KSKXWJ38_j6u'),(86,47,'Fuw_KzC4IPgAURg7FrHruhfDFS3Q'),(88,49,'FkLnBA47FFF6Fv00T3yH95zZU6nr'),(89,50,'FlAQ7H63ZAoyzCfygGRspSrMD1gH'),(90,50,'FqwMaeUtlhrq3TgmPAIi8n-iKV-y'),(91,50,'Fpy07DD0mDtcJsFRVQqHfExn5LC8'),(92,50,'FohoY6OXspKJ7JBkGeSafqXs3e3r'),(93,51,'FrdBIJGoIWn1sK4lXcD4PwETI4P-'),(94,51,'FpM3_OUwbID9O8qHdqfdI9u_detD'),(95,51,'FmIrdSlxtwlp8QjTWa0VmkG8C387'),(96,51,'Fu4KiHUQctCR-XCmFyLRFmfWMEGr'),(97,51,'FmJaB3HG_dQ9vxyAFj1uRdaOuzx_'),(98,52,'FtZdOSOFa8dejIkhf_5FKtV6B4Ro'),(99,52,'Fm3l3wggFZeiCHFmPHnj2edMTQQH'),(100,52,'Flu2X5CTplni9B7uaVmitR39tweC'),(101,52,'FqDpk03FgQn6mSFFJkWF6hzQ3WHw'),(102,52,'FrWIwMhHWy_70b85krZrjfxhdwNT'),(103,52,'Fi6jmuULWdlDkR7yt9ihU-SzBi5U'),(104,53,'Fhn_KKmHqjgNo-pekQh1w3mXau8_'),(105,53,'FpI4shiBphJGUNbs2kaktS8zZKSm'),(106,54,'Ft7OFiTbd3JR9znmQza3g4bDVDi6'),(107,54,'FnKngomYJ79n8zA3Hco3l4-gnLP3'),(108,54,'FioAqfn7f6UB_oR3YlTbGRJ8RJxA'),(109,54,'FvjDMEZwle81Sxd0qau7J-an573M'),(114,57,'FgS_SeRbVz-xXW8dOUok_LnQ1BpG'),(115,58,'FgS_SeRbVz-xXW8dOUok_LnQ1BpG');

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) DEFAULT NULL,
  `user_id_of_seller` int(11) DEFAULT NULL,
  `user_id_of_buyer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4;

/*Data for the table `store` */

insert  into `store`(`id`,`commodity_id`,`user_id_of_seller`,`user_id_of_buyer`) values (127,62,108,108),(128,63,114,117),(129,64,118,120),(131,67,117,121),(132,64,118,123),(133,65,120,124),(135,84,117,119),(136,112,132,119),(137,146,131,130),(140,96,117,136);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(15) NOT NULL,
  `last_login` bigint(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `img` varchar(40) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `describe` varchar(100) DEFAULT NULL,
  `background` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`phoneNum`,`email`,`password`,`last_login`,`nickname`,`img`,`address`,`sex`,`describe`,`background`) values (114,'woddy',NULL,NULL,'123',1608252954062,'Woddy','Frrfe5VmpJxijhjPWNwZvar065QZ','河北师范大学',1,NULL,'FqWlHd5Sz1KtefMc7YBykjuG6kXz'),(117,'6542774',NULL,NULL,'123456',1608199669247,'酌酒','Fk7lxxfCbKQ3naaP8nKbSRrp4hPE','河北大学',1,NULL,'lrBBgyC-UeG7yw2J-ke63vJ_Zyla'),(118,'ywh123',NULL,NULL,'111111',1608201547237,'二郎','FoWA3LAPQRyvA7zh90f1cf74Kx9J','河北师范大学',1,NULL,NULL),(119,'1783460533',NULL,NULL,'123',1608251734523,'彭于晏','Fk7lxxfCbKQ3naaP8nKbSRrp4hPE','河北师范大学',1,NULL,'FlNIxppdGGoVUh7vk5qH3fbPjhfu'),(120,'asd',NULL,NULL,'123456',1608194452941,'柴老','FoF9E2iy2CleKmdgYP-WuNJw4Uyo','河北师范大学',1,NULL,'FpmETxVcORXgu-BBRbPkrjnY7O5B'),(122,'ywh222',NULL,NULL,'222222',1608201570910,'哈利','FoF9E2iy2CleKmdgYP-WuNJw4Uyo','河北师范大学',1,NULL,NULL),(123,'A',NULL,NULL,'123456',1608195129896,'山治','Fn12G84uugpg1_oVi2GYg6kBhHdN','河北师范大学',1,NULL,'Fib1DnJnCMQwQ_sZUgL0MJwJIDey'),(124,'2075301365',NULL,NULL,'123456',1608214888434,'小树林','Fk7lxxfCbKQ3naaP8nKbSRrp4hPE','河北农业大学',1,NULL,'FiIJyPO6geCMCXIM-rW1tnc3dHH4'),(127,'woody1',NULL,NULL,'123',1608253187742,'Tom','FudczKPAlyFdMht2DvKXUWFfvXpr','河北师范大学',1,NULL,NULL),(128,'longlong',NULL,NULL,'123',1608200007421,'乔巴','FhnEwaAcsW7B8jqf3kA4zM5V_OYD','河北师范大学',1,NULL,NULL),(130,'20000217',NULL,NULL,'123456',1608253472283,'Nike','Fs09WWznZqoXlTeQS7p8EURXsqst','河北医科大学',1,NULL,NULL),(131,'69966996',NULL,NULL,'123456',1608269279901,'娜美','FpT0gV6glmtoU2h4mTv22fZuQWDW','河北师范大学',1,NULL,NULL),(132,'hongmao',NULL,NULL,'123',1608206829711,'二货本货','FsKpPmmQmZrxxZ3r1zcamHhR_l9g','河北师范大学',1,NULL,NULL),(133,'20000229',NULL,NULL,'123',1608268320720,'IU','FhCwxjpY5RnjB_Rzr6LBXXZWc7af','河北师范大学',1,NULL,NULL),(134,'122333',NULL,NULL,'123',1608251315639,NULL,NULL,'河北师范大学',1,NULL,NULL),(135,'233444',NULL,NULL,'123',1608252432931,'普朗克','Ftwuc7dNc0fA1_TSCfBW49TT9xsA','河北师范大学',1,NULL,NULL),(136,'1716576990',NULL,NULL,'123456',1608269432510,'鸣人','Ft5ZvMX8x0EqzWO3E2fVndHOYKAX','河北农业大学',1,NULL,'FpB9VTh3B3Ofa6zshwVtTMdCXJAl');

/*Table structure for table `user_like_post` */

DROP TABLE IF EXISTS `user_like_post`;

CREATE TABLE `user_like_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `user_like_post_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_like_post` */

insert  into `user_like_post`(`id`,`post_id`,`user_id`) values (48,41,119),(49,41,123),(50,44,119),(51,44,127),(54,41,127),(56,54,127),(57,53,127),(58,52,127),(59,51,127),(60,50,127),(61,49,127),(63,47,127),(64,46,127),(65,45,127),(69,54,136),(70,53,136);

/*Table structure for table `user_likecom` */

DROP TABLE IF EXISTS `user_likecom`;

CREATE TABLE `user_likecom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `com_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `com_id` (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_likecom` */

insert  into `user_likecom`(`id`,`user_id`,`com_id`) values (42,119,78),(45,119,80),(46,127,80),(47,119,82),(48,131,82),(49,119,85),(50,130,86),(51,130,87),(52,136,88);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
