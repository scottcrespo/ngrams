import sys

def test_mapper():
    """
    Prototype method in python to construct trigrams from 
    a simple string
    """
    
    lines = [
            "the quick brown fox jumped over the lazy dog",
            "the quick brown", "the quick", "the"
            ]
    
    for line in lines:
        
        words = line.split()
        length = len(words)
        sys.stdout.write("\nLength of %d \n-------------------\n" % length)
        
        i = 0
        while (i+2 < length):
                    
            first = words[i]
            second = words [i+1]
            third = words[i+2]

            trigram = "%s %s %s \n" % (first, second, third)
            
            sys.stdout.write(trigram)
            
            i += 1
        
if __name__ == "__main__":
    test_mapper()