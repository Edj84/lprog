package org.example.domainmodel.generator;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
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
import org.example.domainmodel.domainmodel.Type;

@SuppressWarnings("all")
public class DomainmodelGenerator extends AbstractGenerator {
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    fsa.generateFile("DBGen.java", this.DBGen());
    fsa.generateFile("Table.java", this.Table());
    fsa.generateFile("Collumn.java", this.Collumn());
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
    _builder.append("private ArrayList<Table> squema;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public DBGen(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("squema = new ArrayList<Table>();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean addTable(Table table){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return squema.add(table);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence Table() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("public class Table{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ArrayList<Collumn> collumns;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Table(){");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean addCollumn(Collumn col){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return collumns.add(col);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence Collumn() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class Collumn{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String name;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String type;\t\t\t");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public Collumn(){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void setName(String name){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.name = name;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public void setType(String type){");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(type.toUpperCase().equals(\"STRING\"))\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name = \"VARCHAR\";");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.name = name;");
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
    _builder.append("\t\t\t\t\t");
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
    _builder.append("Table ");
    String _name = e.getName();
    _builder.append(_name);
    _builder.append(" = new Table();");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      EList<Feature> _features = e.getFeatures();
      for(final Feature f : _features) {
        _builder.append("\t\t");
        CharSequence _compile = this.compile(f);
        _builder.append(_compile, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compile(final Feature f) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("        ");
    _builder.newLine();
    _builder.append("Collumn col = new Collum();");
    _builder.newLine();
    _builder.append("col.setName(");
    String _name = f.getName();
    _builder.append(_name);
    _builder.append(");\t");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("col.setType(");
    Type _type = f.getType();
    _builder.append(_type, "        ");
    _builder.append(");        ");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
