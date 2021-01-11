class Triangle():
    def __init__(self, p):
        self.p1 = p[0]
        self.p2 = p[1]
        self.p3 = p[2]
        self.b = 0

    def border(self):
        return self.b

    def inc_b(self):
        self.b = self.b + 1


