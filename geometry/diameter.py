from lab1 import area, line_length



def diameter(ch):
    assert len(ch) > 0
    result = []
    max = -1
    k = len(ch)
    ch.append(ch[0])
    i = 1
    temp = 0
    while area(ch[k - 1], ch[0], ch[i + 1]) > area(ch[k - 1], ch[0], ch[i]):
        i = i + 1
    start = i
    j = 0
    while temp < k:
        temp = start
        while area(ch[j], ch[j + 1], ch[temp + 1]) >= area(ch[j], ch[j + 1], ch[temp]):
            temp = temp + 1
            if temp >= k:
                break
        end = temp

        for a in range(start, end + 1):
            if line_length([ch[a], ch[j]]) > max:
                max = line_length([ch[a], ch[j]])
                result = a, j
        j = j + 1
        start = end
    return result
