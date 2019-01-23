package cold.face.test;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.rpc.ServiceException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mytest {

    private static Logger log = LoggerFactory.getLogger(mytest.class);

    public static void main(String[] args) {
        int type = 1;
        switch (type) {
            case 1:
                sendRecieveBill();
                break;
            case 2:
                sendShouldRecieveBill();
                break;
            default:
                break;
        }
    }

    private static void sendShouldRecieveBill() {
        try {
            //产品类别传精斗云，对应编码	JDZBCPLB012
            //kdProductType 这个是产品类别字段
            double rate = 0.06;
            double money = 1;
            double rateMoney = (money / (1 + rate)) * rate;

            BigDecimal bg = new BigDecimal(rateMoney);
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            //单头（billHead）
            String CU = "102SZQS";//控制单元
            String creator = "YGJ";//创建人 JDDJ_jdy
            String createTime = formatter1.format(date);//创建日期
            String lastUpdateUser = "YGJ";//最后修改人 JDDJ_jdy
            String lastUpdateTime = createTime;//最后修改日期
            String number = "YGJ-" + formatter.format(date);//单据编码
            String bizDate = createTime;//业务日期
            String company = "102SZQS";//公司
            String asstActType = "00001";//往来户类型
            String asstActNumber = "30536010";//往来户编码(查数据库)
            String currency = "BB01";//编码（币别）
            String adminOrgUnit = "102SZQS.102SZQS";//部门
            String person = "YGJ";//人员
            double exchangeRate = 1;//汇率
            double amount = 1;//应收金额(查数据库)
            double amountLocal = amount;//应收本位币金额
            double unVerifyAmount = 0;//未结算金额
            double unVerifyAmountLocal = 0;//未结算金额本位币
            int sourceBillType = 2;//来源单据类型
            String bizType = "210";//业务类型
            String paymentType = "002";//付款方式
            double totalTaxAmount = amount;//价税合计
            double taxAmount = f1;//税额
            boolean redBlueType = false;//是否红字发票
            int billType = 101;//单据类型
            String description = "lijingtestordernum";//参考信息:预付单号(查数据库)
            String abstractName = "lijingtestordernum";//摘要:细类(查数据库)
            String sourceRemarks = "精斗云分销线下订货";//来源备注:精斗云分销线下订货
            double lastExhangeRate = 1;//最后调汇汇率
            double totalAmount = amount;//金额合计
            //分录（billEntries）
            int seq = 1;//序列号
            String expenseItem = "001";//费用项目
            double recievePayAmount = amount;//应收金额
            double recievePayAmountLocal = amount;//应收金额本位币
            double unVerifyAmount1 = 0;//未结算金额
            double unVerifyAmountLocal1 = 0;//未结算金额本位币
            double lockUnVerifyAmt = 0;//未锁定金额
            double lockUnVerifyAmtLocal = 0;//未锁定金额本位币
            int quantity = 1;//数量
            double price = amount;//单价
            double actualPrice = amount;//实际含税单价
            double taxRate = 0.06;//税率:查数据库
            double amount1 = amount;//金额
            double amountLocal1 = amount;//金额本位币
            int unwriteOffBaseQty = 0;//未核销基本数量
            double realPrice = amount;//实际单价
            String productName = "会计3.0多账套plus版";//产品名称
            String kdProductType = "JDZBCPLB012";//产品类别
            String measureUnit = "001";//计量单位
            double localUnwriteOffAmount = amount;//未核销本位币金额
            double taxPrice = amount;//含税单价
            //收款计划（receivePlans）
            int seq1 = 1;//序列号
            String receivePayDate = createTime;//应收应付日期
            double receivePayAmount = amount;//应收应付金额
            double receivePayAmountLocal = amount;//应收应付金额本位币

            String request = "<OtherBill>\n" +
                    "\t<billHead>\n" +
                    "\t\t<CU>" + CU + "</CU>\n" +
                    "\t\t<creator>" + creator + "</creator>\n" +
                    "\t\t<createTime>" + createTime + "</createTime>\n" +
                    "\t\t<lastUpdateUser>" + lastUpdateUser + "</lastUpdateUser>\n" +
                    "\t\t<lastUpdateTime>" + lastUpdateTime + "</lastUpdateTime>\n" +
                    "\t\t<number>" + number + "</number>\n" +
                    "\t\t<bizDate>" + bizDate + "</bizDate>\n" +
                    "\t\t<company>" + company + "</company>\n" +
                    "\t\t<asstActType>" + asstActType + "</asstActType>\n" +
                    "\t\t<asstActNumber>" + asstActNumber + "</asstActNumber>\n" +
                    "\t\t<currency>" + currency + "</currency>\n" +
                    "\t\t<adminOrgUnit>" + adminOrgUnit + "</adminOrgUnit>\n" +
                    "\t\t<person>" + person + "</person>\n" +
                    "\t\t<exchangeRate>" + exchangeRate + "</exchangeRate>\n" +
                    "\t\t<amount>" + amount + "</amount>\n" +
                    "\t\t<amountLocal>" + amountLocal + "</amountLocal>\n" +
                    "\t\t<unVerifyAmount>" + unVerifyAmount + "</unVerifyAmount>\n" +
                    "\t\t<unVerifyAmountLocal>" + unVerifyAmountLocal + "</unVerifyAmountLocal>\n" +
                    "\t\t<sourceBillType>" + sourceBillType + "</sourceBillType>\n" +
                    "\t\t<bizType>" + bizType + "</bizType>\n" +
                    "\t\t<paymentType>" + paymentType + "</paymentType>\n" +
                    "\t\t<totalTaxAmount>" + totalTaxAmount + "</totalTaxAmount>\n" +
                    "\t\t<taxAmount>" + taxAmount + "</taxAmount>\n" +
                    "\t\t<redBlueType>" + redBlueType + "</redBlueType>\n" +
                    "\t\t<billType>" + billType + "</billType>\n" +
                    "\t\t<description>" + description + "</description>\n" +
                    "\t\t<abstractName>" + abstractName + "</abstractName>\n" +
                    "\t\t<sourceRemarks>" + sourceRemarks + "</sourceRemarks>\n" +
                    "\t\t<lastExhangeRate>" + lastExhangeRate + "</lastExhangeRate>\n" +
                    "\t\t<totalAmount>" + totalAmount + "</totalAmount>\n" +
                    "</billHead>\n" +
                    "<billEntries>\n" +
                    "\t<entry>\n" +
                    "\t<seq>" + seq + "</seq>\n" +
                    "\t<expenseItem>" + expenseItem + "</expenseItem>\n" +
                    "\t<recievePayAmount>" + recievePayAmount + "</recievePayAmount>\n" +
                    "\t<recievePayAmountLocal>" + recievePayAmountLocal + "</recievePayAmountLocal>\n" +
                    "\t<unVerifyAmount>" + unVerifyAmount1 + "</unVerifyAmount>\n" +
                    "\t<unVerifyAmountLocal>" + unVerifyAmountLocal1 + "</unVerifyAmountLocal>\n" +
                    "\t<lockUnVerifyAmt>" + lockUnVerifyAmt + "</lockUnVerifyAmt>\n" +
                    "\t<lockUnVerifyAmtLocal>" + lockUnVerifyAmtLocal + "</lockUnVerifyAmtLocal>\n" +
                    "\t<quantity>" + quantity + "</quantity>\n" +
                    "\t<price>" + price + "</price>\n" +
                    "\t<actualPrice>" + actualPrice + "</actualPrice>\n" +
                    "\t<taxRate>" + taxRate + "</taxRate>\n" +
                    "\t<amount>" + amount1 + "</amount>\n" +
                    "\t<amountLocal>" + amountLocal1 + "</amountLocal>\n" +
                    "\t<unwriteOffBaseQty>" + unwriteOffBaseQty + "</unwriteOffBaseQty>\n" +
                    "\t<realPrice>" + realPrice + "</realPrice>\n" +
                    "\t<productName>" + productName + "</productName>\n" +
                    "\t<kdProductType>" + kdProductType + "</kdProductType>\n" +
                    "\t<measureUnit>" + measureUnit + "</measureUnit>\n" +
                    "\t<localUnwriteOffAmount>" + localUnwriteOffAmount + "</localUnwriteOffAmount>\n" +
                    "\t<taxPrice>" + taxPrice + "</taxPrice>\n" +
                    "</entry>\n" +
                    "</billEntries>\n" +
                    "<receivePlans>\n" +
                    "\t<ReceivePlan>\n" +
                    "\t\t<seq>" + seq1 + "</seq>\n" +
                    "\t\t<receivePayDate>" + receivePayDate + "</receivePayDate>\n" +
                    "\t\t<receivePayAmount>" + receivePayAmount + "</receivePayAmount>\n" +
                    "\t\t<receivePayAmountLocal>" + receivePayAmountLocal + "</receivePayAmountLocal>\n" +
                    "</ReceivePlan>\n" +
                    "</receivePlans>\n" +
                    "</OtherBill>\n";
            String endpoint = "http://192.168.200.101:7088/ormrpc/services/WSOtherBillFacade?wsdl";
            // 直接引用远程的wsdl文件
            // 以下都是套路
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName("submit");// WSDL里面描述的接口名称
            call.addParameter("xmlData", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 接口的参数
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
            Object result = call.invoke(new String[]{request});
            // 给方法传递参数，并且调用方法
            log.info("result is " + result);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void sendRecieveBill() {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //单头（billHead）
            String cu = "102SZQS";//控制单元
            String creator = "YGJ";//创建人 JDDJ_jdy
            String createtime = formatter1.format(date);//创建日期
            String lastupdateuser = "YGJ";//最后修改人 JDDJ_jdy
            String lastupdatetime = createtime;//最后修改日期
            String number = "YGJ-" + formatter.format(date);//单据编码
            String bizdate = createtime;//业务日期
            String company = "102SZQS";//公司
            int sourcesystype = 103;//业务来源
            int sourcetype = 103;//来源系统
            String currency = "BB01";//币别
            double exchangerate = 1;//汇率
            int fundtype = 100;//收款方式
            int rectype = 100;//收款类型
//            String payeeaccount = "";//收款科目
            String adminorgunit = "102SZQS.102SZQS";//部门
            String payertype = "00001";//往来户类型
            String payernumber = "30536010";//往来户编码(查数据库)
            String description = "lijingtestordernum";//参考信息:预付单号(查数据库)
            String payeeaccountbank = "102SZQS016";//收款账户
//            String payeebank = "";//收款银行
            //分录（billEntries）
            int seq = 1;//序列号
            double amount = 1;//应收金额(查数据库)
            double localamt = amount;//应收本位币金额
            double actualamt = amount;//实收金额
            double actuallocamt = amount;//实收本位币金额
            String remark = "精斗云保证金lijingtest伙伴";
            String request = "<ReceivingBill>\n" +
                    "\t<billHead>\n" +
                    "\t\t<CU>" + cu + "</CU>\n" +
                    "\t\t<creator>" + creator + "</creator>\n" +
                    "\t\t<number>" + number + "</number>\n" +
                    "\t\t<bizdate>" + bizdate + "</bizdate>\n" +
                    "\t\t<createtime>" + createtime + "</createtime>\n" +
                    "\t\t<lastupdateuser>" + lastupdateuser + "</lastupdateuser>\n" +
                    "\t\t<lastupdatetime>" + lastupdatetime + "</lastupdatetime>\n" +
                    "\t\t<company>" + company + "</company>\n" +
                    "\t\t<sourcesystype>" + sourcesystype + "</sourcesystype>\n" +
                    "\t\t<sourcetype>" + sourcetype + "</sourcetype>\n" +
                    "\t\t<currency>" + currency + "</currency>\n" +
                    "\t\t<exchangerate>" + exchangerate + "</exchangerate>\n" +
                    "\t\t<fundtype>" + fundtype + "</fundtype>\n" +
                    "\t\t<rectype>" + rectype + "</rectype>\n" +
//                    "\t\t<payeeaccount>" + payeeaccount + "</payeeaccount>\n" +
                    "\t\t<adminorgunit>" + adminorgunit + "</adminorgunit>\n" +
                    "\t\t<payertype>" + payertype + "</payertype>\n" +
                    "\t\t<payernumber>" + payernumber + "</payernumber>\n" +
                    "\t\t<description>" + description + "</description>\n" +
                    "\t\t<payeeaccountbank>" + payeeaccountbank + "</payeeaccountbank>\n" +
//                    "\t\t<payeebank>" + payeebank + "</payeebank>\n" +
                    "</billHead>\n" +
                    "<billEntries>\n" +
                    "\t<entry>\n" +
                    "\t<seq>" + seq + "</seq>\n" +
                    "\t<amount>" + amount + "</amount>\n" +
                    "\t<localamt>" + localamt + "</localamt>\n" +
                    "\t<actualamt>" + actualamt + "</actualamt>\n" +
                    "\t<actuallocamt>" + actuallocamt + "</actuallocamt>\n" +
                    "\t<remark>" + remark + "</remark>\n" +
                    "</entry>\n" +
                    "</billEntries>\n" +
                    "</ReceivingBill>\n";
            String endpoint = "http://192.168.200.101:7088/ormrpc/services/WSReceiveBillSubmitFacade?wsdl";
            // 直接引用远程的wsdl文件
            // 以下都是套路
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName("submit");// WSDL里面描述的接口名称
            call.addParameter("xmlData", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);// 接口的参数
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
            Object result = call.invoke(new String[]{request});
            // 给方法传递参数，并且调用方法
            log.info("result is " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
