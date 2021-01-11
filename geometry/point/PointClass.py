import random
import pygame
import constants
import lab1
import colors
import polygon.point_in_pol



class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.x_move = random.randrange(constants.MIN_SPEED, constants.MAX_SPEED, 1)
        self.y_move = random.randrange(constants.MIN_SPEED, constants.MAX_SPEED, 1)

    def get_x(self):
        return self.x

    def get_y(self):
        return self.y

    def get_point(self):
        return [self.x, self.y]

    def move(self):
        self.x = self.x + self.x_move
        self.y = self.y + self.y_move

    def draw(self, window):
        pygame.draw.circle(window, colors.BLACK, (round(self.x), round(self.y)), 3)

    def collised_with_simple(self, p):
        k = 0
        if polygon.point_in_pol.octan_test(p, [self.x, self.y]):
            self.x_move = 0
            self.y_move = 0



    def set_move(self, x, y):
        self.x_move = x
        self.y_move = y

    def move_back(self):
        self.x_move = -self.x_move
        self.y_move = - self.y_move

    def collised_with_convex(self, p):

        if not polygon.point_in_pol.binary_test(p, [self.x, self.y]):
            # b = [p[i+1][0]-p[i][0],p[i+1][1]-p[i][0]]
            # a = 2*lab1.vector_mult([self.x_move, self.y_move], b)/lab1.vector_mult(b,b)
            # self.x_move = round(a*b[0] - self.x_move)
            # self.y_move = round(a*b[1] - self.y_move)
            # print(self.x_move, self.y_move)
            self.x_move = -self.x_move
            self.y_move = - self.y_move


    def get_vector(self):
        return [self.x - self.x_move, self.y - self.y_move]
