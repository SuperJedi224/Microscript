# Microscript
The Microscript golfing language

Originally published May 28, 2015

Relocated from Pastebin November 11, 2015

Microscript was my first golfing language design. The language is stack based, operating on two stacks and two registers, with 64-bit integers as the sole data type.

**Instructions:**
```
<any integer literal n not part of another command> -> Increment first register by n

"<any sequence of characters>" -> Push the unicode ids of the characters in the string into the stack, in the order they appear in the string

a -> Empty the currently selected stack, outputting the values popped as characters, in the order popped

c -> Let n be the value currently in the first register when this command is called. Then, zero the first register and repeat the remainder of the program, up to but not including the next "]" character (if one exists, otherwise it goes through the end of the program), n times

C -> Copy all items from the selected stack onto the top of the other stack; such that order is preserved

d<any integer literal n> -> Decrement first register by n

e -> Set the first register to two to the power of its previous value

E -> Set the first register to ten to the power of its previous value

f -> Reverse the order of elements in the selected stack

h -> Halt execution, without outputting register

i -> Take a value from the input and write it to the first register

I -> Take a string from the input and push the unicode IDs of its characters to the selected stack, from left to right

l -> Write the contents of the second register to the first register

n -> Print a newline

o -> Pop a value off of the currently stack and write it to the first register

p -> Print the contents of the first register as a number, followed by a newline (this will also be done automatically when execution is complete, except when halted by the  <code>h</code> command)

P -> Print the contents of the first register as a Unicode character

q -> Works like a, but includes wrapping quotes

r<any integer literal n> -> Increment the first register by a random integer on [0,n)

s -> Push the contents of the register to the stack

t -> Without popping, create a copy of the top value on the stack and write it to the first register

v -> Write the contents of the first register to the second register

x -> Toggle stack selection

z -> Zero the first register

Z -> Empty the selected stack

+ -> Pop a value from the stack and increment the first register by this value

- -> Pop a value from the stack and decrement the first register by this value

* -> Pop a value from the stack and multiply the first register by this value

{ -> Repeatedly run the code between this and the matching "}" (if it exists, the end of the program otherwise), exclusive, until the value of the first register is 0.

[ -> Run the code between this and the next ] if and only if the selected stack is empty

! -> Set the first register to 1 if its previous value was 0, 0 otherwise

% -> Pop a value from the stack and take the first register modulo this value

/ -> Pop a value from the stack and divide the first register by this value, rounding towards 0 if needed

# ->Find the current size of the selected stack and write to the first register

$ ->As c, but repeats only the next single symbol

'<any one character> -> Write the unicode id of that character to the first register
```

![](https://i.creativecommons.org/l/by-sa/4.0/88x31.png)

This work is licensed under a [Creative Commons Attribution-ShareAlike 4.0 International License.](http://creativecommons.org/licenses/by-sa/4.0/)
