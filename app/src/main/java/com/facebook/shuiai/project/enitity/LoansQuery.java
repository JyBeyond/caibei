package com.facebook.shuiai.project.enitity;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/9/17
 * 贷款列表的查询类
 */

public class LoansQuery extends BaseQuery {
    private String lendMoney;//额度
    private String lendPerid;//范围
    private String monthlyInterestRate;//月利率
    private String onlineTime;//上线时间
    private String creditStanding;//使用情况
    private String hasCredit;//有无信用卡
    private String lendTotalMoney;//首页输入跳转参数
    private String lendTotalPerid;//首页输入跳转参数
    private String throughputRate;//放款速度
}
