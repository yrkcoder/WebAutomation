package pages

import org.openqa.selenium.{By, WebDriver}

class MainArticlePage(driver: WebDriver) {

  private def mainHeading = driver.findElement(By.id("firstHeading"))
  private def articleSubHeadings =  driver.findElements(By.className("mw-headline"))


  def getAllSubHeadingTexts(): IndexedSeq[String] = {
    (0 until articleSubHeadings.size()).map(i => articleSubHeadings.get(i).getText)
  }

  def getMainHeading():String = {
    mainHeading.getText
  }

}
