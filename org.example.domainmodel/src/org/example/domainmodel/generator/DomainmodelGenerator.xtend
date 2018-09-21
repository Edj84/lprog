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
        fsa.generateFile("Statment.java", Statment())
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
 			private ArrayList<Statment> statments;
 			
 			public DBGen(){
 				statments = new ArrayList<Statments>();
 			}
 			
 			public boolean addStmt(Statment stmt){
 				return statments.add(stmt);
 			}
 	''' 
 	
 	def Statment() '''
 		
 		public class Statment(){
 			private String type, ID;
 			private ArrayList<String> content;
 			
 			public Statment(String ID){
 				this.ID = ID;
 			}
 			
 			public void setType(String type){
 			 	this.type = type;
 			}
 			
 			public void addContent(String content){
 			 	this.content.add(content);
 			}
 			
 			public String getID(){
 				return ID;
 			}
 			
 			public String getType(){
 			 	return type;
 			}
 			
 			public ArrayList<String> getContent(){
 				return content;
 			}
 			
 			public String toString(){
 				
 				StringBuilder content = new StringBuilder();
 				
 				for(c : this.content)
 					content.append(c + " ");
 				
 				return ID + "{ " 
 				+ type + ", " 
 				+ content;
 			} 		
 			
 		}
 		
 	'''
 	 	
    def App(Resource resource) '''
		public class App{
			
			public static void main(String[] args){
				DBGen db = new DBGen();
				Statment stmt;			
			}
			
			public static void readStatment(Statment stmt){
				stmt.
			}
		
		«FOR e : resource.allContents.toIterable.filter(Entity)»
			«compile(e)»
		«ENDFOR»
		
		}
	'''
    
    def compile(Entity e) ''' 
        
        stmt = readStmt(«e.name»);
        
        «FOR f : e.features»
        	«compile(f)»
        «ENDFOR»
        }
    '''
 
    def compile(Feature f) '''
        
                
        
    '''
}