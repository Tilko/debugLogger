/*******************************************************************************
 * Copyright 2020 Grégoire Martinetti
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package api_global.logUtility;

import java.util.Collection;
import java.util.function.Function;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.stream.Stream;

//import org.apache.logging.log4j.LogManager;

import api_global.strUtil.StringGen;


public class L {
	//private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(L.class);
	
	public static int indent = 0;
	public static void inc() {indent++;}
	public static void dec() {indent--;}
	protected static String ind() {
		return StringGen.makeStrConcating(indent, "    ");
	}
	public static void l(Object o) {
		//System.console().printf(ind() + o);
		//System.out
		//logger.log(Level.ALL, o.toString());
		//Logger.getGlobal().log(Level.ALL, o.toString());
		System.out.println(ind() + o);
	}
	public static <T> void l(Stream<T> s) {
		encap("", ()-> s.forEach(obj -> l(obj)));
	}
	public static <T> void l(T[] s) {
		encap("", ()-> {
			for(T o : s) {	L.l(o);	}
		});
	}
	public static <T> void l(Stream<T> s, Function<T,Object> extractor) {
		l(s.map(extractor));
	}
	public static <T> void l(Collection<T> objs, Function<T,Object> extractor) {
		l(objs.stream().map(extractor));
	}
	
	public static void encap(Object o, Runnable r) {
		System.out.println(ind() + o + "{");
		inc();
		r.run();
		dec();
		l("}");
	}
	public static void e(Object o, Exception e) {
		L.l(o);
		e.printStackTrace();
	}
	public static void e(Exception e) {
		e.printStackTrace();
	}
	public static void e(Object o) {
		System.err.println(ind() + o);
	}
	public static void w(Object o) {
		System.err.println(o);
	}
}
