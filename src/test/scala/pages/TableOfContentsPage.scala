package pages

import java.util

import helpers.Utilities._
import org.openqa.selenium.{By, WebDriver, WebElement}

class TableOfContentsPage(driver: WebDriver) {

  def tocListElement = driver.findElement(By.xpath("//*[@id=\"toc\"]/ul"))

  def tocListItems: util.List[WebElement] = tocListElement.findElements(By.className("toctext"))

  def tocListItemLinks: util.List[WebElement] = tocListElement.findElements(By.tagName("A"))

  def getTableOfContentLinkByText(text: String): Option[WebElement] = {
    getSubelementLinkText(tocListElement, text)
  }

  def getAllTableOfContentsTexts(): IndexedSeq[String] = {
     (0 until tocListItems.size()).map(i => tocListItems.get(i).getText)
  }
}
