package steps;

import PageObjects.CreateTaskPage;
import PageObjects.TasksListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.BaseTest;

import java.net.MalformedURLException;

public class CreateNewTask extends BaseTest {
    CreateTaskPage createTaskPage;
    TasksListPage tasksListPage;

    @Given("Click Add new Task")
    public void clickAddNewTask() throws MalformedURLException {
        Android_setUp();
        tasksListPage = new TasksListPage(driver);
        createTaskPage = new CreateTaskPage(driver);
        tasksListPage.clickAddTaskBtn();
    }

    @And("Enter Taskname")
    public void enterTaskName() {
        createTaskPage.enterTaskName("Task 1");
    }

    @And("Enter TaaskDesc")
    public void enterTaaskDesc() {
        createTaskPage.enterTaskDesc("Desc 1");
    }

    @When("Click Save")
    public void clickSave() {
        createTaskPage.clickSaveBtn();
    }

    @Then("Task add successfully")
    public void taskAddSuccessfully() {
        tearDown();
    }
}
