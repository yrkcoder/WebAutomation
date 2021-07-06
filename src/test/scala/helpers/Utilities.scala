package helpers

import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.interactions.Actions

import scala.util.Try

object Utilities {

  def getSubelementLinkText(element:WebElement, text: String): Option[WebElement] = {
    Try {
      element.findElement(By.partialLinkText(text))
    }.toOption
  }

  def mouseOverElement(driver:WebDriver,element: WebElement) = {
    val action = new Actions(driver)
    action.moveToElement(element).perform
  }
}
