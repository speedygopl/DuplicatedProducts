package com.codebin;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Program {

    // method gets text from chosen file
    public List<String> readAllLinesFromFile() throws IOException {
        return Files.readAllLines(Paths.get(App.path));
    }

    //method removes unnecessary text from file
    public List<String> returnSelectedLines() throws IOException {
        List<String> allLines = readAllLinesFromFile();
        return allLines.stream()
                .filter(x -> !(x.contains("range_labels"))
                        && !(x.contains("range,2"))
                        && !(x.contains("transaction_labels"))
                        && !(x.contains("position_labels,transactionId,Name,Quantity"))
                        && !(x.contains("subtransaction_labels,")))
                .map(x -> x.replaceAll("transaction,,", ""))
                .map(x -> x.replaceAll("subtransaction,", ""))
                .map(x -> x.replaceAll("position,", ""))
                .map(x -> x.replaceAll(",[0-9]{8}", ""))
                .filter(x -> x.length() < 62)
                .collect(Collectors.toList());
    }

    // method return the list which contains only id number of item and its quantity
    public List<String> returnQuantityList() throws IOException {
        List<String> allLines = returnSelectedLines();
        List<String> quantityList;
        quantityList = allLines.stream()
                .filter(x -> x.contains(",,"))
                .collect(Collectors.toList());
        return quantityList;

    }

    // method returns the list of items' names with corresponding id numbers
    public List<String> returnProductList() throws IOException {
        List<String> allLines = returnSelectedLines();
        List<String> productList;
        productList = allLines.stream()
                .filter(x -> !x.contains(",,"))
                .collect(Collectors.toList());
        return productList;

    }

    // method produce one list of product and quantities using two lists
// code fits products and quantities by id number
    public List<String> returnProductAndQuantityList() throws IOException {
        List<String> productList = returnProductList();
        List<String> quantityList = returnQuantityList();
        List<String> productAndQuantityList = new ArrayList<>();
        for (int i = 0; i < quantityList.size(); i++) {
            String[] quantityArray = quantityList.get(i).split(",,");
            for (int j = 0; j < productList.size(); j++) {
                if (productList.get(j).contains(quantityArray[0])) {
                    productAndQuantityList.add(i, productList.get(j).concat(":::" + quantityArray[1]));
                    productList.remove(j);
                }
            }
        }
        return productAndQuantityList;

    }

    //    method return duplicated items with their quantities and remove not repeated items
    public List<String> getListOfRepeatedNamesAndQuantities() throws IOException {
        List<String> productAndQuantityList = returnProductAndQuantityList();
        List<String> resultList = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        Map<String, Integer> namesAndQuantitiesMap = new HashMap<>();
        productAndQuantityList = productAndQuantityList.stream()
                .map(x -> x.replaceAll("[0-9]{8},", ""))
                .collect(Collectors.toList());
        for (int i = 0; i < productAndQuantityList.size(); i++) {
            String[] nameAndQuantityArray = productAndQuantityList.get(i).split(":::");
            if (!namesAndQuantitiesMap.containsKey(nameAndQuantityArray[0])) {
                namesAndQuantitiesMap.put(nameAndQuantityArray[0], Integer.parseInt(nameAndQuantityArray[1]));

            } else {
                namesAndQuantitiesMap.put(nameAndQuantityArray[0],
                        namesAndQuantitiesMap.get(nameAndQuantityArray[0])
                                + Integer.parseInt(nameAndQuantityArray[1]));
                if (!resultList.contains(nameAndQuantityArray[0]))
                    resultList.add(nameAndQuantityArray[0]);
            }
        }

        for (int i = 0; i < resultList.size(); i++) {
            finalList.add(resultList.get(i).concat(":::" + namesAndQuantitiesMap.get(resultList.get(i)).toString()));
        }
        return finalList;

    }

    // Method writes to file output result of application and return result String
// that is showed by app
    public String writeResultToFile() throws IOException {
        List<String> finalList = getListOfRepeatedNamesAndQuantities();
        String resultOutput = "";
        for (String line : finalList) {
            resultOutput += line + System.lineSeparator();
        }
        FileWriter fileWriter = new FileWriter("c:/users/damazy/downloads/output.txt");
        fileWriter.write(resultOutput);
        fileWriter.close();
        return resultOutput;
    }


}



