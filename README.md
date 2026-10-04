# Assignment 3 - Bridge Pattern

## Student Information

Name: Dias Sabit  
Group: SE-2526  
Course: Software Design Patterns  
Assignment: Assignment 3 - Bridge Pattern  
Topic: A - Drawing

## Repository

GitHub: https://github.com/diassabit1/SDP-Bridge-pattern

## Base Commit

Base commit: e6250f8

The base version contains the two-by-two Bridge implementation using:

- Circle + VectorRenderer
- Circle + RasterRenderer
- Square + VectorRenderer
- Square + RasterRenderer

## Bridge Structure

### Abstraction side

- Shape - abstract base class
- Circle - refined abstraction
- Square - refined abstraction

### Implementation side

- Renderer - implementor interface
- VectorRenderer - concrete implementor
- RasterRenderer - concrete implementor
- AsciiRenderer - extension concrete implementor

### Client

- Main

## Source Files

- src/Shape.java
- src/Circle.java
- src/Square.java
- src/Renderer.java
- src/VectorRenderer.java
- src/RasterRenderer.java
- src/AsciiRenderer.java
- src/Main.java

## Bridge Field

The bridge is the interface-typed field:

`protected Renderer renderer;`

The `Shape` class receives the Renderer through its constructor.

## execute()

Each concrete shape implements `execute()` and delegates rendering to the Renderer implementation.

Circle:

`return renderer.renderCircle(radius);`

Square:

`return renderer.renderSquare(side);`

## setImplementation()

`Shape` provides:

`public void setImplementation(Renderer renderer)`

This method allows the implementation to be replaced at runtime.

## Runtime Switching Test

T5 uses the same Circle object and changes its Renderer from VectorRenderer to RasterRenderer.

Expected behavior:

- the same Shape object remains in use
- the Circle id remains unchanged
- the Circle radius remains unchanged
- the rendering implementation changes

The demo verifies this with:

`sameObject=true`

and

`stateUnchanged=true`.

## Extension

The extension adds AsciiRenderer as the third implementation.

The existing Shape, Circle, Square, Renderer, VectorRenderer and RasterRenderer classes were not changed for the extension.

The extension is demonstrated by:

- T6: Circle + AsciiRenderer
- T7: Square + AsciiRenderer

The extension diff is stored in:

`extension.diff`

## Build and Run

From the extracted assignment folder:

```text
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
Expected Demo Result
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
T4 PASS | Square + RasterRenderer | result=RASTER square side=3
T5 PASS | sameObject=true | stateUnchanged=true | before=VECTOR circle radius=2 | after=RASTER circle radius=2
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3
SUMMARY: 7/7 PASS
Test Mapping
Test	Description
T1	Circle + VectorRenderer
T2	Circle + RasterRenderer
T3	Square + VectorRenderer
T4	Square + RasterRenderer
T5	Runtime implementation switching
T6	Circle + AsciiRenderer
T7	Square + AsciiRenderer
Submission Files
src/
sources.txt
README.md
report.pdf
demo-output.txt
extension.diff
