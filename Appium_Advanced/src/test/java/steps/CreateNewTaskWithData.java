package steps;

import PageObjects.CreateTaskPage;
import PageObjects.TasksListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.BaseTest;

import java.net.MalformedURLException;

public class CreateNewTaskWithData extends BaseTest {

    CreateTaskPage createTaskPage;
    TasksListPage tasksListPage;

    @Given("Click add new Task")
    public void clickAddNewTask() throws MalformedURLException {
        Android_setUp();
        tasksListPage = new TasksListPage(driver);
        createTaskPage = new CreateTaskPage(driver);
        tasksListPage.clickAddTaskBtn();
    }

    @Given("Enter {string} and {string}")
    public void enterAnd(String taskName, String taskDesc){
        createTaskPage.enterTaskName(taskName);
        createTaskPage.enterTaskDesc(taskDesc);
    }

    @Then("Task Added Successfully")
    public void taskAddedSucessfully(){
        tearDown();
    }
}
