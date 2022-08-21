encodedString = input("Enter the encoded string: ")
possibleKeys = range(26)

for key in possibleKeys:
  decodedString = ""
  for char in encodedString:
    if char.isalpha():
      if char.islower():  
        decodedString += chr((ord(char) - key - 97) % 26 + 97)
      else:
        decodedString += chr((ord(char) - key - 65) % 26 + 65)
    else:
      decodedString += char
  

  print(f"key: {key}, str: {decodedString}")