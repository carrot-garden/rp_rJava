// :tabSize=2:indentSize=2:noTabs=false:folding=explicit:collapseFolds=1:
import javax.swing.JFrame ;
import java.awt.Point ;
import java.lang.reflect.Constructor ;
import javax.swing.JButton ;
import javax.swing.ImageIcon ;
import java.lang.reflect.* ;


public class RJavaTools_Test {

	/* so that we can check about access to private fields and methods */
	private int bogus = 0 ; 
	private int getBogus(){ return bogus ; }
	private static int staticbogus ;
	private static int getStaticBogus(){ return staticbogus ; } 
	
	public int x = 0 ; 
	public static int static_x = 0; 
	public int getX(){ return x ; }
	public static int getStaticX(){ return static_x ; }
	public void setX( Integer x ){ this.x = x.intValue();  } 
	
	// {{{ main 
	public static void main( String[] args){
		
		System.out.println( "Testing RJavaTools.getConstructor" ) ;
		try{
			constructors() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.classHasField" ) ;
		try{
			classhasfield() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
	
		System.out.println( "Testing RJavaTools.classHasMethod" ) ;
		try{
			classhasmethod() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.hasMethod" ) ;
		try{
			hasmethod() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.isStatic" ) ;
		try{
			isstatic() ;
		} catch( TestException e ){
			fails(e) ; 
		}
		success() ;  
		
		System.out.println( "Testing RJavaTools.getCompletionName" ) ;
		try{
			getcompletionname() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.getFieldNames" ) ;
		try{
			getfieldnames() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.getMethodNames" ) ;
		try{
			getmethodnames() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.getStaticFields" ) ;
		try{
			getstaticfields() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.getStaticMethods" ) ;
		try{
			getstaticmethods() ;
		} catch( TestException e ){
			fails(e);  
		}
		success() ;
		
		System.out.println( "Testing RJavaTools.getMethod" ) ;
		System.out.println( "NOT YET AVAILABLE" ) ;
		
		System.out.println( "Testing RJavaTools.newInstance" ) ;
		System.out.println( "NOT YET AVAILABLE" ) ;
		
		System.out.println( "Testing RJavaTools.invokeMethod" ) ;
		System.out.println( "NOT YET AVAILABLE" ) ;
		
	}
	// }}}

	// {{{ fails 
	private static void fails( TestException e ){
		System.err.println( "\n" ) ;
		e.printStackTrace() ;
		System.err.println( "FAILED" ) ; 
	}
	// }}}
	
	// {{{ success
	private static void success(){
		System.out.println( "PASSED" ) ;    
	}
	// }}}
	
	// {{{ @Test getFieldNames
	private static void getfieldnames() throws TestException{
		String[] names; 
		
		// {{{ getFieldNames(Point, false) -> c('x', 'y' )
		System.out.print( "    * getFieldNames(Point, false)" ) ;
		names = RJavaTools.getFieldNames( Point.class, false ) ;
		if( names.length != 2 ){
			throw new TestException( "getFieldNames(Point, false).length != 2" ) ;
		}
		for( int i=0; i<2; i++){
			if( !( "x".equals(names[i]) || "y".equals(names[i] ) ) ){
				throw new TestException( "getFieldNames(Point, false).length != c('x','y') " ) ;
			}
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		// {{{ getFieldNames(Point, true ) --> character(0)
		System.out.print( "    * getFieldNames(Point, true )" ) ;
		names = RJavaTools.getFieldNames( Point.class, true ) ;
		if( names.length != 0 ){
			throw new TestException( "getFieldNames(Point, true ) != character(0)" ); 
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		// {{{ getFieldNames(RJavaTools_Test, true ) --> static_x 
		System.out.print( "    * getFieldNames(RJavaTools_Test, true )" ) ;
		names = RJavaTools.getFieldNames( RJavaTools_Test.class, true ) ;
		if( names.length != 1 ){
			throw new TestException( "getFieldNames(RJavaTools_Test, true ).length != 1" ); 
		}
		if( ! "static_x".equals( names[0] ) ){
			throw new TestException( "getFieldNames(RJavaTools_Test, true )[0] != 'static_x' " );
		}
		System.out.println( " : ok " ) ; 
		// }}}
		
		// {{{ getFieldNames(RJavaTools_Test, false ) --> c('x', 'static_x') 
		System.out.print( "    * getFieldNames(RJavaTools_Test, false )" ) ;
		names = RJavaTools.getFieldNames( RJavaTools_Test.class, false ) ;
		if( names.length != 2 ){
			throw new TestException( "getFieldNames(RJavaTools_Test, false ).length != 2" ); 
		}
		for( int i=0; i<2; i++){
			if( ! ( "x".equals( names[i] ) || "static_x".equals(names[i]) ) ){
				throw new TestException( "getFieldNames(RJavaTools_Test, false ) != c('x', 'static_x') " );
			}
		}
		System.out.println( " : ok " ) ;
		// }}}
		
	}
	// }}}
	
	// {{{ @Test getMethodNames
	private static void getmethodnames() throws TestException{
		String[] names ; 
		
		// {{{ getMethodNames(RJavaTools_Test, true) -> c('getStaticX()', 'main(' )
		System.out.print( "    * getMethodNames(RJavaTools_Test, true)" ) ;
		names = RJavaTools.getMethodNames( RJavaTools_Test.class, true ) ;
		if( names.length != 2 ){
			throw new TestException( "getMethodNames(RJavaTools_Test, true).length != 2 (" + names.length + ")" ) ;
		}
		for( int i=0; i<2; i++){
			if( !( "getStaticX()".equals(names[i]) || "main(".equals(names[i] ) ) ){
				throw new TestException( "getMethodNames(RJavaTools_Test, true) != c('getStaticX()','main(') " ) ;
			}
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		// {{{ getMethodNames(Object, true) -> character(0) )
		System.out.print( "    * getMethodNames(Object, true)" ) ;
		names = RJavaTools.getMethodNames( Object.class, true ) ;
		if( names.length != 0 ){
			throw new TestException( "getMethodNames(Object, true).length != 0 (" + names.length + ")" ) ;
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		// {{{ getMethodNames(RJavaTools_Test, false) %contains% { "getX()", "getStaticX()", "setX(", "main(" }
		System.out.print( "    * getMethodNames(RJavaTools_Test, false)" ) ;
		names = RJavaTools.getMethodNames( RJavaTools_Test.class, false ) ;
		int cpt = 0;
		String[] expected = new String[]{ "getX()", "getStaticX()", "setX(", "main(" }; 
		for( int i=0; i<names.length; i++){
			for( int j=0; j<expected.length; j++ ){
				if( names[i].equals( expected[j] ) ){
						cpt++; 
						break ; /* the j loop */
				}
			}
		}
		if( cpt != expected.length ){
			throw new TestException( "getMethodNames(RJavaTools_Test, false) did not contain expected methods " ) ; 
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		
	}
	// }}}
	
	// {{{ @Test getCompletionName
	private static void getcompletionname() throws TestException{
		
		Field f ; 
		Method m ; 
		String name ; 
		
		// {{{ getCompletionName( RJavaTools_Test.x )
		try{
			System.out.print( "    * getCompletionName(RJavaTools_Test.x)" ) ;
			f = RJavaTools_Test.class.getField("x") ;
			if( ! "x".equals(RJavaTools.getCompletionName( f ) ) ){
				throw new TestException( "getCompletionName(RJavaTools_Test.x) != 'x' " ) ; 
			}
			System.out.println( " == 'x' : ok " ) ;
		} catch( NoSuchFieldException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
		
		// {{{ getCompletionName( RJavaTools_Test.static_x )
		try{
			System.out.print( "    * getCompletionName(RJavaTools_Test.static_x)" ) ;
			f = RJavaTools_Test.class.getField("static_x") ;
			if( ! "static_x".equals(RJavaTools.getCompletionName( f ) ) ){
				throw new TestException( "getCompletionName(RJavaTools_Test.static_x) != 'static_x' " ) ; 
			}
			System.out.println( " == 'static_x' : ok " ) ;
		} catch( NoSuchFieldException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
		
		// {{{ getCompletionName( RJavaTools_Test.getX() )
		try{
			System.out.print( "    * getCompletionName(RJavaTools_Test.getX() )" ) ;
			m = RJavaTools_Test.class.getMethod("getX", (Class[])null ) ;
			if( ! "getX()".equals(RJavaTools.getCompletionName( m ) ) ){
				System.err.println( RJavaTools.getCompletionName( m ) );  
				throw new TestException( "getCompletionName(RJavaTools_Test.getX() ) != ''getX()' " ) ; 
			}
			System.out.println( " == 'getX()' : ok " ) ;
		} catch( NoSuchMethodException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
		
		// {{{ getCompletionName( RJavaTools_Test.setX( Integer ) )
		try{
			System.out.print( "    * getCompletionName(RJavaTools_Test.setX( Integer ) )" ) ;
			m = RJavaTools_Test.class.getMethod("setX", new Class[]{ Integer.class } ) ;
			if( ! "setX(".equals(RJavaTools.getCompletionName( m ) ) ){
				System.err.println( RJavaTools.getCompletionName( m ) );  
				throw new TestException( "getCompletionName(RJavaTools_Test.setX(Integer) ) != 'setX(' " ) ; 
			}
			System.out.println( " == 'setX(' : ok " ) ;
		} catch( NoSuchMethodException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
		
	}
	// }}}
	
	// {{{ @Test isStatic
	private static void isstatic() throws TestException{
		Field f ; 
		Method m ; 
		
		// {{{ isStatic(RJavaTools_Test.x) -> false
		try{
			System.out.print( "    * isStatic(RJavaTools_Test.x)" ) ;
			f = RJavaTools_Test.class.getField("x") ;
			if( RJavaTools.isStatic( f) ){
				throw new TestException( "isStatic(RJavaTools_Test.x) == true" ) ; 
			}
			System.out.println( " = false : ok " ) ;
		} catch( NoSuchFieldException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
		
		// {{{ isStatic(RJavaTools_Test.getX) -> true
		try{
			System.out.print( "    * isStatic(RJavaTools_Test.getX() )" ) ;
			m = RJavaTools_Test.class.getMethod("getX", (Class[])null ) ;
			if( RJavaTools.isStatic( m ) ){
				throw new TestException( "isStatic(RJavaTools_Test.getX() ) == false" ) ; 
			}
			System.out.println( " = false : ok " ) ;
		} catch( NoSuchMethodException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
		
		// {{{ isStatic(RJavaTools_Test.static_x) -> true
		try{
			System.out.print( "    * isStatic(RJavaTools_Test.static_x)" ) ;
			f = RJavaTools_Test.class.getField("static_x") ;
			if( ! RJavaTools.isStatic( f) ){
				throw new TestException( "isStatic(RJavaTools_Test.static_x) == false" ) ; 
			}
			System.out.println( " = true  : ok " ) ;
		} catch( NoSuchFieldException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
	
		// {{{ isStatic(RJavaTools_Test.getStaticX) -> true
		try{
			System.out.print( "    * isStatic(RJavaTools_Test.getStaticX() )" ) ;
			m = RJavaTools_Test.class.getMethod("getStaticX", (Class[])null ) ;
			if( ! RJavaTools.isStatic( m ) ){
				throw new TestException( "isStatic(RJavaTools_Test.getStaticX() ) == false" ) ; 
			}
			System.out.println( " = true  : ok " ) ;
		} catch( NoSuchMethodException e){
			throw new TestException( e.getMessage() ) ;
		}
		// }}}
	
	}
	// }}}
	
  // {{{ @Test constructors 
	private static void constructors() throws TestException {
		/* constructors */ 
		Constructor cons ;
		boolean error ; 
		
		// {{{ getConstructor( String, null )
		System.out.print( "    * getConstructor( String, null )" ) ;
		try{
			cons = RJavaTools.getConstructor( String.class, (Class[])null ) ;
		} catch( Exception e ){
			throw new TestException( "getConstructor( String, null )" ) ; 
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		// {{{ getConstructor( Integer, { String.class } ) 
		System.out.print( "    * getConstructor( Integer, { String.class } )" ) ;
		try{
			cons = RJavaTools.getConstructor( Integer.class, new Class[]{ String.class } ) ;
		} catch( Exception e){
			throw new TestException( "getConstructor( Integer, { String.class } )" ) ; 
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		// {{{ getConstructor( JButton, { String.class, ImageIcon.class } )
		System.out.print( "    * getConstructor( JButton, { String.class, ImageIcon.class } )" ) ;
		try{
			cons = RJavaTools.getConstructor( JButton.class, new Class[]{ String.class, ImageIcon.class } ) ;
		} catch( Exception e){
			throw new TestException( "getConstructor( JButton, { String.class, ImageIcon.class } )" ) ; 
		}
		System.out.println( " : ok " ) ;
		// }}}
		
		// {{{ getConstructor( Integer, null ) -> exception 
		error = false ; 
		System.out.print( "    * getConstructor( Integer, null )" ) ;
		try{
			cons = RJavaTools.getConstructor( Integer.class, (Class[])null ) ;
		} catch( Exception e){
			 error = true ; 
		}
		if( !error ){
			throw new TestException( "getConstructor( Integer, null ) did not generate error" ) ;
		}
		System.out.println( " -> exception : ok " ) ;
		// }}}
		
		// {{{ getConstructor( JButton, { String.class, JButton.class } ) -> exception
		error = false ; 
		System.out.print( "    * getConstructor( JButton, { String.class, JButton.class } )" ) ;
		try{
			cons = RJavaTools.getConstructor( JButton.class, new Class[]{ String.class, JButton.class } ) ;
		} catch( Exception e){
			 error = true ; 
		}
		if( !error ){
			throw new TestException( "getConstructor( JButton, { String.class, JButton.class } ) did not generate error" ) ;
		}
		System.out.println( " -> exception : ok " ) ;
		// }}}
		
	}
	// }}}
	
	// {{{ @Test methods
	private static void methods() throws TestException{
		
	}
	// }}}
	
	// {{{ @Test hasfields
	private static void hasfield() throws TestException{
		
		Point p = new Point() ; 
		System.out.println( "    java> Point p = new Point()" ) ; 
		System.out.print( "    * hasField( p, 'x' ) " ) ; 
		if( !RJavaTools.hasField( p, "x" ) ){
			throw new TestException( " hasField( Point, 'x' ) == false" ) ;
		}
		System.out.println( " true : ok" ) ;
		
		System.out.print( "    * hasField( p, 'iiiiiiiiiiiii' ) " ) ; 
		if( RJavaTools.hasField( p, "iiiiiiiiiiiii" ) ){
			throw new TestException( " hasField( Point, 'iiiiiiiiiiiii' ) == true" ) ;
		}
		System.out.println( "  false : ok" ) ;
		
		/* testing a private field */
		RJavaTools_Test ob = new RJavaTools_Test(); 
		System.out.print( "    * testing a private field " ) ; 
		if( RJavaTools.hasField( ob, "bogus" ) ){
			throw new TestException( " hasField returned true on private field" ) ;
		}
		System.out.println( "  false : ok" ) ;
		
		
	}
	// }}}
	
	// {{{ @Test hasmethod
	private static void hasmethod() throws TestException{
		
		Point p = new Point() ; 
		System.out.println( "    java> Point p = new Point()" ) ; 
		System.out.print( "    * hasMethod( p, 'move' ) " ) ; 
		if( !RJavaTools.hasMethod( p, "move" ) ){
			throw new TestException( " hasField( Point, 'move' ) == false" ) ;
		}
		System.out.println( " true : ok" ) ;
		
		System.out.print( "    * hasMethod( p, 'iiiiiiiiiiiii' ) " ) ; 
		if( RJavaTools.hasMethod( p, "iiiiiiiiiiiii" ) ){
			throw new TestException( " hasMethod( Point, 'iiiiiiiiiiiii' ) == true" ) ;
		}
		System.out.println( "  false : ok" ) ;
		
		/* testing a private method */
		RJavaTools_Test ob = new RJavaTools_Test(); 
		System.out.print( "    * testing a private method " ) ; 
		if( RJavaTools.hasField( ob, "getBogus" ) ){
			throw new TestException( " hasMethod returned true on private method" ) ;
		}
		System.out.println( "  false : ok" ) ;

	}
	// }}}

	// {{{ @Test classhasfield
	private static void classhasfield() throws TestException{

	}
	// }}}
	
	// {{{ @Test classhasmethod
	private static void classhasmethod() throws TestException{

		System.out.print( "    * classHasMethod( RJavaTools_Test, 'getX', false ) " ) ;
		if( ! RJavaTools.classHasMethod( RJavaTools_Test.class, "getX", false ) ){
			throw new TestException( " classHasMethod( RJavaTools_Test, 'getX', false ) == false" ) ;
		}
		System.out.println( "  true : ok" ) ;

		System.out.print( "    * classHasMethod( RJavaTools_Test, 'getStaticX', false ) " ) ;
		if( ! RJavaTools.classHasMethod( RJavaTools_Test.class, "getStaticX", false ) ){
			throw new TestException( " classHasMethod( RJavaTools_Test, 'getStaticX', false ) == false" ) ;
		}
		System.out.println( "  true : ok" ) ;

		System.out.print( "    * classHasMethod( RJavaTools_Test, 'getX', true ) " ) ;
		if( RJavaTools.classHasMethod( RJavaTools_Test.class, "getX", true ) ){
			throw new TestException( " classHasMethod( RJavaTools_Test, 'getX', true ) == true (non static method)" ) ;
		}
		System.out.println( "  false : ok" ) ;

		System.out.print( "    * classHasMethod( RJavaTools_Test, 'getStaticX', true ) " ) ;
		if( ! RJavaTools.classHasMethod( RJavaTools_Test.class, "getStaticX", true ) ){
			throw new TestException( " classHasMethod( RJavaTools_Test, 'getStaticX', true ) == false (static)" ) ;
		}
		System.out.println( "  true : ok" ) ;

		System.out.print( "    * classHasMethod( RJavaTools_Test, 'getBogus', false ) " ) ;
		if( RJavaTools.classHasMethod( RJavaTools_Test.class, "getBogus", false ) ){
			throw new TestException( " classHasMethod( RJavaTools_Test, 'getBogus', false ) == true (private method)" ) ;
		}
		System.out.println( "  false : ok" ) ;

		System.out.print( "    * classHasMethod( RJavaTools_Test, 'getStaticBogus', true ) " ) ;
		if( RJavaTools.classHasMethod( RJavaTools_Test.class, "getStaticBogus", true ) ){
			throw new TestException( " classHasMethod( RJavaTools_Test, 'getBogus', true ) == true (private method)" ) ;
		}
		System.out.println( "  false : ok" ) ;

	}
	// }}}
	
	// {{{ @Test getstaticfields
	private static void getstaticfields() throws TestException{
		Field[] f ;
		
		System.out.print( "    * getStaticFields( RJavaTools_Test ) " ) ;
		f = RJavaTools.getStaticFields( RJavaTools_Test.class ) ;
		if( f.length != 1 ){
			throw new TestException( " getStaticFields( RJavaTools_Test ).length != 1" ) ;
		}
		if( ! "static_x".equals( f[0].getName() ) ){
			throw new TestException( " getStaticFields( RJavaTools_Test )[0] != 'static_x'" ) ;
		}
		System.out.println( "  : ok" ) ;
		
		System.out.print( "    * getStaticFields( Object ) " ) ;
		f = RJavaTools.getStaticFields( Object.class ) ;
		if( f != null ){
			throw new TestException( " getStaticFields( Object ) != null" ) ;
		}
		System.out.println( "  : ok" ) ;
		
	}
	// }}}
	
	// {{{ @Test getstaticmethods
	private static void getstaticmethods() throws TestException{
		Method[] m ;
		
		// {{{ getStaticMethods( RJavaTools_Test )
		System.out.print( "    * getStaticMethods( RJavaTools_Test ) " ) ;
		m = RJavaTools.getStaticMethods( RJavaTools_Test.class ) ;
		String[] expected = new String[]{ "getStaticX" , "main" };
		int count = 0; 
		if( m.length != expected.length ){
			throw new TestException( " getStaticMethods( RJavaTools_Test ).length != 2" ) ;
		}
		for( int i=0; i<m.length; i++){
			for( int j=0; j<expected.length; j++ ){
				if( m[i].getName().equals( expected[j] ) ){
					count++; 
					break ; // the j loop
				}
			}
		}
		if( count != expected.length ){
			throw new TestException( " getStaticMethods( RJavaTools_Test ) != c('main', 'getStaticX') " ) ;
		}
		System.out.println( "  : ok" ) ;
		// }}}
		
		// {{{ getStaticMethods( Object )
		System.out.print( "    * getStaticMethods( Object ) " ) ;
		m = RJavaTools.getStaticMethods( Object.class ) ;
		if( m != null ){
			throw new TestException( " getStaticMethods( Object ) != null" ) ;
		}
		System.out.println( "  : ok" ) ;
		// }}}
		
	}
	// }}}
	
	// {{{ TestException class
	private static class TestException extends Exception{
		public TestException( String message ){
			super( message ) ;
		}
	}
	// }}}
	
}
