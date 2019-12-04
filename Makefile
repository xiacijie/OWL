CC = g++
INCLUDE = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/darwin
CPPFLAGS = -w -std=c++11 $(INCLUDE)
LDLIBS = -ljvm
LDFLAGS = -L$(JAVA_HOME)/jre/lib -L$(JAVA_HOME)/jre/lib/server -Wl,-rpath -Wl,$(JAVA_HOME)/jre/lib/server
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
