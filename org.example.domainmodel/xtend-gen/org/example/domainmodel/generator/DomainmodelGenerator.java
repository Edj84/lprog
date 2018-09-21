package org.example.domainmodel.generator;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.example.domainmodel.domainmodel.Entity;
import org.example.domainmodel.domainmodel.Feature;

@SuppressWarnings("all")
public class DomainmodelGenerator extends AbstractGenerator {
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    fsa.generateFile("DBGen.java", this.DBGen());
    fsa.generateFile("Statment.java", this.Statment());
    fsa.generateFile("App.java", this.App(resource));
  }
  
  /**
   * for (e : resource.allContents.toIterable.filter(Entity)) {
   * fsa.generateFile(
   * "DBGen.java",
   * e.compile)
   * }
   */
  public CharSequence DBGen() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class DBGen {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ArrayList<Statment> statments;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public DBGen(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("statments = new ArrayList<Statments>();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean addStmt(Statment stmt){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return statments.add(stmt);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence Statment() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("public class Statment(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String type, ID;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ArrayList<String> content;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Statment(String ID){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.ID = ID;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setType(String type){");
    _builder.newLine();
    _builder.append("\t \t");
    _builder.append("this.type = type;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void addContent(String content){");
    _builder.newLine();
    _builder.append("\t \t");
    _builder.append("this.content.add(content);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getID(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ID;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getType(){");
    _builder.newLine();
    _builder.append("\t \t");
    _builder.append("return type;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ArrayList<String> getContent(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return content;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String toString(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("StringBuilder content = new StringBuilder();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for(c : this.content)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("content.append(c + \" \");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ID + \"{ \" ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("+ type + \", \" ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("+ content;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} \t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence App(final Resource resource) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class App{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void main(String[] args){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("DBGen db = new DBGen();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Statment stmt;\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void readStatment(Statment stmt){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stmt.");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      Iterable<Entity> _filter = Iterables.<Entity>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), Entity.class);
      for(final Entity e : _filter) {
        CharSequence _compile = this.compile(e);
        _builder.append(_compile);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compile(final Entity e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("stmt = readStmt(");
    String _name = e.getName();
    _builder.append(_name);
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compile(final Feature f) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("                ");
    _builder.newLine();
    _builder.append("        ");
    _builder.newLine();
    return _builder;
  }
}
