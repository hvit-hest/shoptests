package org.restore.pages.commoncomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CommonTable {

    private WebDriver driverHere;
    private By headerBy = By.cssSelector("tr.header");
    private By tableBy = By.cssSelector("table");
    private By recordBy = By.cssSelector("tr.row");
    private By cellBy = By.cssSelector("td");

    public CommonTable(WebDriver myPersonalDriver) {
        this.driverHere = myPersonalDriver;
    }

    public CommonTable(WebDriver myPersonalDriver, By tableBy, By recordBy, By cellBy) {
        this.driverHere = myPersonalDriver;
        this.tableBy = tableBy;
        this.recordBy = recordBy;
        this.cellBy = cellBy;
    }

    public WebElement getTable() { return driverHere.findElement(tableBy); }

    public List<WebElement> getRows() { return getTable().findElements(recordBy); }

    public WebElement getRow(int row) { return getRows().get(row); }

    public List<WebElement> getRowCells(int row) { return getRow(row).findElements(cellBy); }

    public List<WebElement> getRowCells(WebElement row) { return row.findElements(cellBy); }

    public WebElement getCell(int row, int cell) { return getRowCells(row).get(cell); }

    public List<WebElement> getColumn(int column) {
        return getRows().stream().map(r -> getRowCells(r).get(column)).collect(toList()); }

    public List<String> getColumnsText(int column) {
        return getRows().stream().map(r -> getRowCells(r).get(column).getText().trim()).collect(toList());
    }

    public List<String> getColumnsText(int column, By locator) {
        return getRows().stream().map(r -> getRowCells(r).get(column).
                findElement(locator).getText().trim()).collect(toList());
    }

    private List<WebElement> getCellsByText(String textInCell) {
        return getTable().findElements(cellBy).stream().
                filter(r -> r.getText().contains(textInCell)).collect (toList());
    }

    public WebElement getCellByText(String textInCell) {
        return getCellsByText(textInCell).get(0);
    }

    private List<WebElement> getRowsByText(String textInRow) {
        return getRows().stream().filter(r -> r.getText().contains(textInRow)).collect(toList());
    }

    public WebElement getRowByText(String textInRow) { return getRowsByText(textInRow).get(0);}

    public WebElement getCellByTextFromColumn(int column, String textInCell) {
        return getRowCells(getRowByText(textInCell)).get(column);
    }
}
