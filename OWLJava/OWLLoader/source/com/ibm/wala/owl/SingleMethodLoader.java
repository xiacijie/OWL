package com.ibm.wala.owl;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.ibm.wala.classLoader.ArrayClassLoader;
import com.ibm.wala.classLoader.CallSiteReference;
import com.ibm.wala.classLoader.IBytecodeMethod;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IClassLoader;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.classLoader.Language;
import com.ibm.wala.classLoader.Module;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.shrikeBT.ExceptionHandler;
import com.ibm.wala.shrikeBT.IInstruction;
import com.ibm.wala.shrikeBT.IndirectionData;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.ssa.SSAInstructionFactory;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.Descriptor;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeName;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.types.annotations.Annotation;
import com.ibm.wala.util.collections.NonNullSingletonIterator;
import com.ibm.wala.util.config.SetOfClasses;
import com.ibm.wala.util.strings.Atom;

public class SingleMethodLoader implements IClassLoader {
	private final IClassLoader parent;
	private final IClassHierarchy cha;
	private final ClassLoaderReference ref;
	
	private final IBytecodeMethod<IInstruction> singleMethod = new IBytecodeMethod<IInstruction>() {

		public String toString() {
			return "single method " + getReference();
		}
		
		@Override
		public boolean isSynchronized() {
			return false;
		}

		@Override
		public boolean isClinit() {
			return false;
		}

		@Override
		public boolean isInit() {
			return false;
		}

		@Override
		public boolean isNative() {
			return false;
		}

		@Override
		public boolean isWalaSynthetic() {
			return false;
		}

		@Override
		public boolean isSynthetic() {
			return false;
		}

		@Override
		public boolean isAbstract() {
			return false;
		}

		@Override
		public boolean isPrivate() {
			return false;
		}

		@Override
		public boolean isProtected() {
			return false;
		}

		@Override
		public boolean isPublic() {
			return true;
		}

		@Override
		public boolean isFinal() {
			return true;
		}

		@Override
		public boolean isBridge() {
			return false;
		}

		@Override
		public MethodReference getReference() {
			return  MethodReference.findOrCreate(singleClass.getReference(), Atom.findOrCreateUnicodeAtom("main"), Descriptor.findOrCreateUTF8("([Ljava/lang/String;)V"));
		}

		@Override
		public boolean hasExceptionHandler() {
			return false;
		}

		@Override
		public TypeReference getParameterType(int i) {
			assert i == 0;
			return TypeReference.findOrCreateArrayOf(TypeReference.JavaLangString);
		}

		@Override
		public TypeReference getReturnType() {
			return TypeReference.Void;
		}

		@Override
		public int getNumberOfParameters() {
			return 1;
		}

		@Override
		public TypeReference[] getDeclaredExceptions() throws InvalidClassFileException, UnsupportedOperationException {
			return new TypeReference[0];
		}

		@Override
		public int getLineNumber(int bcIndex) {
			return -1;
		}

		@Override
		public SourcePosition getSourcePosition(int instructionIndex) throws InvalidClassFileException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SourcePosition getParameterSourcePosition(int paramNum) throws InvalidClassFileException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getLocalVariableName(int bcIndex, int localNumber) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getSignature() {
			return getReference().getSignature();
		}

		@Override
		public Selector getSelector() {
			return getReference().getSelector();
		}

		@Override
		public Descriptor getDescriptor() {
			return getReference().getDescriptor();
		}

		@Override
		public boolean hasLocalVariableTable() {
			return false;
		}

		@Override
		public IClass getDeclaringClass() {
			return singleClass;
		}

		@Override
		public Atom getName() {
			return getReference().getName();
		}

		@Override
		public boolean isStatic() {
			return true;
		}

		@Override
		public Collection<Annotation> getAnnotations() {
			return Collections.emptySet();
		}

		@Override
		public IClassHierarchy getClassHierarchy() {
			return cha;
		}

		@Override
		public int getBytecodeIndex(int i) throws InvalidClassFileException {
			return -1;
		}

		@Override
		public int getInstructionIndex(int bcIndex) throws InvalidClassFileException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ExceptionHandler[][] getHandlers() throws InvalidClassFileException {
			return new ExceptionHandler[0][];
		}

		@Override
		public IInstruction[] getInstructions() throws InvalidClassFileException {
			return OWLTestRunner.instsToRun;
		}

		@Override
		public Collection<CallSiteReference> getCallSites() throws InvalidClassFileException {
			return Collections.emptySet();
		}

		@Override
		public IndirectionData getIndirectionData() {
			return null;
		}

		@Override
		public Collection<Annotation>[] getParameterAnnotations() {
			return null;
		}

		@Override
		public Collection<Annotation> getAnnotations(boolean runtimeVisible) throws InvalidClassFileException {
			return null;
		}
		
	};
	
	private final IClass singleClass = new IClass() {

		public String toString() {
			return "single class " + getReference();
		}
		
		@Override
		public IClassHierarchy getClassHierarchy() {
			return cha;
		}

		@Override
		public IClassLoader getClassLoader() {
			return SingleMethodLoader.this;
		}

		@Override
		public boolean isInterface() {
			return false;
		}

		@Override
		public boolean isAbstract() {
			return false;
		}

		@Override
		public boolean isPublic() {
			return true;
		}

		@Override
		public boolean isPrivate() {
			return false;
		}

		@Override
		public boolean isSynthetic() {
			return false;
		}

		@Override
		public int getModifiers() throws UnsupportedOperationException {
			return 0;
		}

		@Override
		public IClass getSuperclass() {
			return cha.getRootClass();
		}

		@Override
		public Collection<? extends IClass> getDirectInterfaces() {
			return Collections.emptySet();
		}

		@Override
		public Collection<IClass> getAllImplementedInterfaces() {
			return Collections.emptySet();
		}

		@Override
		public IMethod getMethod(Selector selector) {
			if (singleMethod.getSelector().equals(selector)) {
				return singleMethod;
			} else {
				return null;
			}
		}

		@Override
		public IField getField(Atom name) {
			return null;
		}

		@Override
		public IField getField(Atom name, TypeName type) {
			return null;
		}

		@Override
		public TypeReference getReference() {
			return TypeReference.findOrCreate(SingleMethodLoader.this.getReference(), "Loperation");
		}

		@Override
		public String getSourceFileName() throws NoSuchElementException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Reader getSource() throws NoSuchElementException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IMethod getClassInitializer() {
			return null;
		}

		@Override
		public boolean isArrayClass() {
			return false;
		}

		@Override
		public Collection<? extends IMethod> getDeclaredMethods() {
			return Collections.singleton(singleMethod);
		}

		@Override
		public Collection<IField> getAllInstanceFields() {
			return Collections.emptySet();
		}

		@Override
		public Collection<IField> getAllStaticFields() {
			return Collections.emptySet();
		}

		@Override
		public Collection<IField> getAllFields() {
			return Collections.emptySet();
		}

		@Override
		public Collection<? extends IMethod> getAllMethods() {
			return Collections.singleton(singleMethod);
		}

		@Override
		public Collection<IField> getDeclaredInstanceFields() {
			return Collections.emptySet();
		}

		@Override
		public Collection<IField> getDeclaredStaticFields() {
			return Collections.emptySet();
		}

		@Override
		public TypeName getName() {
			return getReference().getName();
		}

		@Override
		public boolean isReferenceType() {
			return true;
		}

		@Override
		public Collection<Annotation> getAnnotations() {
			return Collections.emptySet();
		}	
	};
	
	public SingleMethodLoader(ClassLoaderReference loader,
		      ArrayClassLoader arrayClassLoader,
		      IClassLoader parent,
		      SetOfClasses exclusions,
		      IClassHierarchy cha) {
		this.ref = loader;
		this.cha = cha;
		this.parent = parent;
	}

	@Override
	public IClass lookupClass(TypeName className) {
		if (singleClass.getName().equals(className)) {
			return singleClass;
		} else {
			return parent.lookupClass(className);
		}
	}

	@Override
	public ClassLoaderReference getReference() {
		return ref;
	}

	@Override
	public Iterator<IClass> iterateAllClasses() {
		return new NonNullSingletonIterator<>(singleClass);
	}

	@Override
	public int getNumberOfClasses() {
		return 1;
	}

	@Override
	public Atom getName() {
		return ref.getName();
	}

	@Override
	public Language getLanguage() {
		return Language.JAVA;
	}

	@Override
	public SSAInstructionFactory getInstructionFactory() {
		return getLanguage().instructionFactory();
	}

	@Override
	public int getNumberOfMethods() {
		return 1;
	}

	@Override
	public String getSourceFileName(IMethod method, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getSource(IMethod method, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSourceFileName(IClass klass) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getSource(IClass klass) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IClassLoader getParent() {
		return parent;
	}

	@Override
	public void init(List<Module> modules) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAll(Collection<IClass> toRemove) {
		// TODO Auto-generated method stub

	}

	public String toString() {
		return "single loader " + getReference();
	}
	

}
