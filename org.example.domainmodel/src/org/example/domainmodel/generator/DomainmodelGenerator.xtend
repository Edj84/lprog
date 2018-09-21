package org.example.domainmodel.generator
 
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.example.domainmodel.domainmodel.Entity
import org.example.domainmodel.domainmodel.Feature
import org.eclipse.xtext.naming.IQualifiedNameProvider
 
import com.google.inject.Inject
 
class DomainmodelGenerator extends AbstractGenerator {
 
    @Inject extension IQualifiedNameProvider
 
    override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
        
        fsa.generateFile("DBGen.java", DBGen)
        fsa.generateFile("Table.java", Table)
        fsa.generateFile("Collumn.java", Collumn)
        fsa.generateFile("App.java", App(resource))
        
        
    }
        /* 
         for (e : resource.allContents.toIterable.filter(Entity)) {
            fsa.generateFile(
                "DBGen.java",
                e.compile)
        }
        
        */
    
 	def DBGen() '''
 		public class DBGen {
 			private ArrayList<Table> squema;
 			
 			public DBGen(){
 				squema = new ArrayList<Table>();
 			}
 			
 			public boolean addTable(Table table){
 				return squema.add(table);
 			}
 	''' 
 	
 	def Table() '''
		
		public class Table{
			private ArrayList<Collumn> collumns;

			public Table(){

			}

			public boolean addCollumn(Collumn col){
				return collumns.add(col);
			}
			
	'''
	
	def Collumn()'''
		public class Collumn{
			private String name;
			private String type;			
		
		public Collumn(){
					
		}

		public void setName(String name){
			this.name = name;
		}

		public void setType(String type){
			
			if(type.toUpperCase().equals("STRING"))			
				name = "VARCHAR";
				
			this.name = name;
		}

	'''
 	 	 	
    def App(Resource resource) '''
		public class App{
			
			public static void main(String[] args){
				DBGen db = new DBGen();
							
		«FOR e : resource.allContents.toIterable.filter(Entity)»
			«compile(e)»
		«ENDFOR»
		
		}
	'''
    
    def compile(Entity e) ''' 
        
        Table «e.name» = new Table();
        
		«FOR f: e.features» 
			«compile(f)»
		«ENDFOR»
        
    '''
 
    def compile(Feature f) '''
        
		Collumn col = new Collum();
		col.setName(«f.name»);	
        col.setType(«f.type»);        
    '''
}