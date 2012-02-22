package controllers;

import play.*;
import play.i18n.Lang;
import play.mvc.*;

import java.util.*;

import apple.laf.JRSUIConstants.ShowArrows;

import models.*;


public class Application extends Controller {

	/**
	 * Index
	 */
	public static void index() {
		render();
	}
}