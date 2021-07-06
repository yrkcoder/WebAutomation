package tests

import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import pages.{TableOfContentsPage, MainArticlePage, SidebarPage}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class WikiArticleTests extends AnyWordSpec with Matchers with BeforeAndAfterAll {

  //Note: Just for simplicity, the chromedriver was included in the source directory
  //System.setProperty("webdriver.chrome.driver","~/chromedriver")
  val driver = new ChromeDriver

  override def beforeAll():Unit =  {
    driver.get("https://en.wikipedia.org/wiki/Metis_(mythology)")
  }

  override def afterAll(): Unit = {
    driver.quit();
  }

  "The headings listed in contents box" should {
    def contentsPage = new TableOfContentsPage(driver)
    def mainArticlePage = new MainArticlePage(driver)
    "be used as headings in the page" in {
      val tableOfContents = contentsPage.getAllTableOfContentsTexts()
      val mainArticleSubHeadings = mainArticlePage.getAllSubHeadingTexts()

      tableOfContents should equal(mainArticleSubHeadings)
    }
    "have functioning hyperlinks" in {
      val tableOfContents = contentsPage.getAllTableOfContentsTexts()
      tableOfContents.foreach{ text =>
        val isElement: Option[WebElement] =  contentsPage.getTableOfContentLinkByText(text)
        isElement shouldBe defined
      }
    }
  }

  "In the Personified concepts" when {
    val sidebarPage = new SidebarPage(driver)
    "Nike has a pop up" should {
      "as specified text" in {
        val isPopupText =  sidebarPage.getPopupTextOnMouseOverLinkText("Nike")
        isPopupText shouldBe Some("In ancient Greek civilization, Nike was a goddess who personified victory. Her Roman equivalent was Victoria.")
      }
    }

    "clicking on Nike" should {
      "navigate to page with Family Tree" in {
        val nikeLink = sidebarPage.getSidebarElementLinkText("Nike")

        nikeLink.foreach(_.click())

        def currentPage = new MainArticlePage(driver)

        currentPage.getMainHeading() shouldBe("Nike (mythology)")
        currentPage.getAllSubHeadingTexts should contain("Family tree")
      }
    }
  }
}
