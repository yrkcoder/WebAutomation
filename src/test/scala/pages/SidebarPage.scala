package pages

import helpers.Utilities._
import java.util.concurrent.TimeUnit
import org.openqa.selenium.{By, WebDriver, WebElement}

class SidebarPage(driver: WebDriver) {

  def sideBarElement = driver.findElement(By.cssSelector("table[class='sidebar nomobile'"))

  def getSidebarElementLinkText(text: String): Option[WebElement] = {
    getSubelementLinkText(sideBarElement, text)
  }

  def getPopupTextOnMouseOverLinkText(text:String):Option[String]= {
    val element = getSidebarElementLinkText(text)
    element.map{ e =>
      mouseOverElement(driver,e)
      def popupExtract = driver.findElement(By.className("mwe-popups-extract"))

      driver.manage.timeouts.implicitlyWait(20, TimeUnit.SECONDS)
      popupExtract.getText
    }
  }
}
