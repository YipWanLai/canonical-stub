package com.awin.canonical;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;


public class CanonicalResponseTransformer {

    private int pageSize = 20;

    public String prepareContent(List<Integer> transactionIds, int pageNumber) {
        List<String> transactionString = new LinkedList<String>();
        for (Integer it : transactionIds) {
            transactionString.add(
                    transactionTemplate().replaceAll("__transactionId__", String.valueOf(it)));
        }

        return contentTemplate()
                .replaceAll("__pageNumber__", String.valueOf(pageNumber))
                .replaceAll("__pagesAvailable__", String.valueOf(pagesAvailable(transactionIds.size(), pageSize)))
                .replaceAll("__totalRowsAvailable__", String.valueOf(transactionIds.size()))
                .replaceAll("__transactionTemplate__", StringUtils.join(transactionString, ","));
    }

    public int pagesAvailable(int length, int pageSize) {
        BigDecimal l = new BigDecimal(length);
        BigDecimal r = new BigDecimal(pageSize);
        BigDecimal result = l.divide(r, RoundingMode.CEILING);
        return result.intValue();
    }

    private String contentTemplate() {
        BufferedReader r = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("contentTemplate.txt")));
        return r.lines().collect(Collectors.joining());
    }

    private String transactionTemplate() {
        BufferedReader r = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("transactionTemplate.txt")));
        return r.lines().collect(Collectors.joining());
    }

    private String contentTemplate2() {
        String content = "{\n" +
                "  \"status\": \"OK\",\n" +
                "  \"message\": \"Found 1 matching transactions\",\n" +
                "  \"payload\": {\n" +
                "    \"pageNumber\": __pageNumber__,\n" +
                "    \"pagesAvailable\": __pagesAvailable__,\n" +
                "    \"totalRowsAvailable\": __totalRowsAvailable__,\n" +
                "    \"pageItems\": [\n" +
                "__transactionTemplate__\n" +
                "    ]\n" +
                "  },\n" +
                "  \"feedbackCode\": 11107\n" +
                "}\n";
        return content;
    }

    private String transactionTemplate2() {
        String content = "{\n" +
                "\t\"id\": __transactionId__,\n" +
                "\t\"advertiserId\": 900,\n" +
                "\t\"publisherId\": 54840,\n" +
                "\t\"groupId\": 9,\n" +
                "\t\"bannerId\": 9,\n" +
                "\t\"productId\": 0,\n" +
                "\t\"clickThroughTime\": \"2019-07-05 07:43:03\",\n" +
                "\t\"date\": \"2019-07-05 07:43:03\",\n" +
                "\t\"commissionDate\": \"2019-07-05 10:13:20\",\n" +
                "\t\"status\": 1,\n" +
                "\t\"type\": 5,\n" +
                "\t\"ip\": \"\",\n" +
                "\t\"referer\": null,\n" +
                "\t\"saleAmount\": 1.00,\n" +
                "\t\"commissionAmount\": 5.00,\n" +
                "\t\"extra\": \"CW-180362563\",\n" +
                "\t\"clickRef\": \"\",\n" +
                "\t\"secondTierRef\": 0,\n" +
                "\t\"paymentId\": null,\n" +
                "\t\"invoiceId\": null,\n" +
                "\t\"paidStatus\": {\n" +
                "\t  \"value\": 0\n" +
                "\t},\n" +
                "\t\"source\": {\n" +
                "\t  \"value\": 12\n" +
                "\t},\n" +
                "\t\"platform\": \"aw\",\n" +
                "\t\"checksum\": null,\n" +
                "\t\"voucherCode\": \"\",\n" +
                "\t\"parts\": [\n" +
                "\t  {\n" +
                "\t\t\"id\": 1087723820,\n" +
                "\t\t\"transactionId\": __transactionId__,\n" +
                "\t\t\"commissionGroupId\": 41280,\n" +
                "\t\t\"code\": \"CALLWINDOW_240\",\n" +
                "\t\t\"saleAmount\": 1.00,\n" +
                "\t\t\"commissionAmount\": 5.00,\n" +
                "\t\t\"payType\": 0,\n" +
                "\t\t\"cpoAmount\": null,\n" +
                "\t\t\"date\": \"2019-07-05 10:13:20\"\n" +
                "\t  }\n" +
                "\t],\n" +
                "\t\"networkFee\": {\n" +
                "\t  \"percentage\": 0.00,\n" +
                "\t  \"amount\": 0.00\n" +
                "\t},\n" +
                "\t\"isTenancy\": false,\n" +
                "\t\"tenancy\": null,\n" +
                "\t\"cpo\": null,\n" +
                "\t\"lastModified\": \"2019-07-05 09:13:20\",\n" +
                "\t\"commissionSharingRule\": null,\n" +
                "\t\"membershipSoft\": false,\n" +
                "\t\"serviceProviderSharedTransaction\": false,\n" +
                "\t\"tqsOrBonus\": false,\n" +
                "\t\"okToDeleteForBonusOrTqs\": false\n" +
                "  }\n";
        return content;
    }

}
