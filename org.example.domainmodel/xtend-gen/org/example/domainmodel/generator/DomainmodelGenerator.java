package org.example.domainmodel.generator;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.example.domainmodel.domainmodel.Entity;

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
  public CharSequence App(final Resource resource) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field compile is undefined for the type Entity");
  }
  
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
    throw new Error("Unresolved compilation problems:"
      + "\nmissing \'(\' at \'f\'"
      + "\nmismatched input \':\' expecting \';\'"
      + "\nUnreachable expression.");
  }
  
  private Procedure1<? super Entity> f;
}
