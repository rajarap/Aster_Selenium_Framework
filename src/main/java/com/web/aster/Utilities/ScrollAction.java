package com.web.aster.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.web.aster.Base.BaseClass;

public class ScrollAction extends BaseClass
{
	public JavascriptExecutor js;
	
	public ScrollAction() {
//		js = (JavascriptExecutor) getDriver();
	}

	public void scrollDown() {
		js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,350)", "");
	}
	
	public void scrollDownTwice() {
		js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,700)", "");
	}
	
	public void scrollUp() {
		js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,-350)", "");
	}
	
	public void ScrollTillElementVisible(WebElement e) {
		js = ((JavascriptExecutor) getDriver());
		js.executeScript("arguments[0].scrollIntoView();", e);
	}
	
	public void ScrollToBottom() {
		js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

}


