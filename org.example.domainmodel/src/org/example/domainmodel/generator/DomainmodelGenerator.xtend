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
        fsa.generateFile("Statment.java", Statment)
        fsa.generateFile("App.java", App(resource))
        
        
    }
        /* 
         for (e : resource.allContents.toIterable.filter(Entity)) {
            fsa.generateFile(
                "DBGen.java",
                e.compile)
        }
        
        */
    
		
	def App(Resource resource) '''
		public class App{
			
			public static void main(String[] args){
				DBGen db = new DBGen();
			}
		}
		«for (e : resource.allContents.toIterable.filter(Entity)){
			e.compile
		}»
	'''
	
 	
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
 	
 	def Statment()'''
 		public class Statment(){
 			private String type, id;
 			private ArrayList<String> content;
 			
 			public Statment(){
 				
 			}
 			
 			«for f : resource.allContents.toIterable.filter(Entity)»
 			   public void set«f.name.toFirstUpper»(«f.type.fullyQualifiedName» «f.name») {
 			   	this.«f.name» = «f.name»;
 			   }
 			
 		}
 		
 	'''
 	
 	
    def compile(Entity e) ''' 
        
        Statment stmt = new Statment();
            «FOR f : e.features»
                «f.compile»
            «ENDFOR»
        }
    '''
 
    def compile(Feature f) '''
        private «f.type.fullyQualifiedName» «f.name»;
        
        public «f.type.fullyQualifiedName» get«f.name.toFirstUpper»() {
            return «f.name»;
        }
        
        public void set«f.name.toFirstUpper»(«f.type.fullyQualifiedName» «f.name») {
            this.«f.name» = «f.name»;
        }
    '''
}