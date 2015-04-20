import sys

def test_mapper():
    """
    Prototype method in python to construct trigrams from 
    a simple string
    """
    
    lines = ["the quick brown fox jumped over the lazy dog","the quick brown","the quick"]
    
    for line in lines:
        
        words = line.split()
        length = len(words)
        sys.stdout.write("\nLength of %d \n-------------------\n" % length)

        for i, word in enumerate(words, 0):

            if i <= 1:
                first = "#"
            else: first = words[i]

            if i==0 or i+1==length:
                second = "#"
            else: second = words[i+1]

            if i+2==length or i+1 ==length:
                third = "#"
            else: third = words[i+2]

            i += 1

            trigram = "%s %s %s \n" % (first, second, third)
            
            sys.stdout.write(trigram)
        
if __name__ == "__main__":
    test_mapper()