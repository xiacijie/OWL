CC = g++
INCLUDE = -I/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/include -I/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/include/darwin
CPPFLAGS = -w -std=c++11 $(INCLUDE)
LDLIBS = -ljli -ljvm
LDFLAGS = -L/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib -L/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib/server
SOURCES = $(wildcard src/*.cpp)
OBJECTS = $(SOURCES:%.c=%.o)

.PHONY: all clean
all: OWL
clean:
	rm src/*.o OWL
%.o: %.cpp
	${CC} ${LDFLAGS} ${CPPFLAGS} ${LDLIBS} -c $< -o $@

OWL: $(OBJECTS)
	$(CC) ${LDFLAGS} $(CPPFLAGS) ${LDLIBS} -o OWL $(OBJECTS)
