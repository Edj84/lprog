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
        for (e : resource.allContents.toIterable.filter(Entity)) {
            fsa.generateFile(
                e.fullyQualifiedName.toString("/") + ".java",
                e.compile)
        }
    }
 
    def compile(Entity e) ''' 
        «IF e.eContainer.fullyQualifiedName !== null»
            package «e.eContainer.fullyQualifiedName»;
        «ENDIF»
        
        public class «e.name» «IF e.superType !== null»extends «e.superType.fullyQualifiedName» «ENDIF»{                               
        
        private ArrayList<Row> rows = new ArrayList<Row>();                  
                            
                            «FOR f : e.features»
                                «f.compile»
                            «ENDFOR»
         }        
    '''
 
    def compile(Feature f) '''
        
        Row «f.name» = new Row(«f.name»,«f.type.fullyQualifiedName»); 
        rows.add(«f.name»);
    '''
}

/* 
  CREATE SEQUENCE contacts_seq
  START WITH 1
  INCREMENT BY 1;
  
 */
 
 /*
  DROP TABLE IF EXISTS 
   */
   
   
