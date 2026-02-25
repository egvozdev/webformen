package steps;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
//import io.cucumber.messages.types.DataTable;
import org.testng.Assert;
import pages.HomePage;


import java.util.List;
import java.util.Map;

import java.util.List;
import java.util.Map;

public class SearchSteps {

    HomePage homePage = new HomePage();

    @Given("User opens Verbformen homepage")
    public void open_homepage() {
        homePage.open();
    }

    @When("User enters word {string}")
    public void enter_word(String word) {
        homePage.search(word);
    }

    @Then("Russian translation should be displayed")
    public void verify_translation() {
        Assert.assertTrue(homePage.isRussianTranslationDisplayed(),
                "Russian translation was not displayed!");
    }
    @Then("Translation should contain {string}")
    public void verify_specific_translation(String word) {
        Assert.assertTrue(homePage.isTranslationDisplayed(word),
                "Russian translation was not displayed!");
    }

    @Then("table {string} should contain data:")
    public void verifyTableData(String tableName, DataTable dataTable) {
        // 1. Получаем данные из веб-интерфейса (например, через Selenium)
        //List<Map<String, String>> actualData = {"ich", "gehe"};
        System.out.println("Go");
        // 2. Преобразуем ожидаемую таблицу в список Map
        //List<Map<String, String>> expectedData = expectedTable.asMaps(String.class, String.class);

        // 3. Проверяем, что все ожидаемые строки есть в фактических
        //Assert.assertTrue(actualData).containsAll(expectedData);
        // Получаем список словарей: каждый словарь — это одна строка таблицы
        // Ключи = заголовки колонок (из первой строки таблицы)
//        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<Map<String, String>> rows = dataTable.asMaps();

        // Пример: просто выводим для отладки
        System.out.println("Таблица '" + tableName + "' содержит следующие данные:");
        for (Map<String, String> row : rows) {
            System.out.println("  " + row.get("Pronoun") + " → " + row.get("Form"));
        }

        // Реальная проверка (пример)
        // Предположим, у вас есть метод, который возвращает актуальные данные таблицы с сайта
        Map<String, String> expectedForms = homePage.getActualFormsFromPage();  // ваша реализация

        for (Map<String, String> expectedRow : rows) {
            String pronoun = expectedRow.get("Pronoun");
            String expectedForm = expectedRow.get("Form");

            String actualForm = expectedForms.get(pronoun);
            // assertEquals или ваша assertion-библиотека
            if (!expectedForm.equals(actualForm)) {
                throw new AssertionError(
                        "Для местоимения '" + pronoun + "' ожидалась форма '" +
                                expectedForm + "', но найдена '" + actualForm + "'"
                );
            }
        }
    }

    // Заглушка — ваша реальная логика парсинга таблицы со страницы
//    private Map<String, String> getActualFormsFromPage() {
//
//        List<WebElement> cells = driver.findElements(By.xpath("//table[@id='users']//tr[1]/td"));
//
//        List<String> cellValues = cells.stream()
//                .map(cell -> cell.getText().replaceAll("\\s+", " ").trim())
//                .collect(Collectors.toList());
//        // Здесь selenium / playwright / jsoup — парсинг HTML-таблицы
//        return Map.of(
//                "ich", "gehe",
//                "du", "gehst",
//                "er/sie/es", "geht"
//        );
//    }

}