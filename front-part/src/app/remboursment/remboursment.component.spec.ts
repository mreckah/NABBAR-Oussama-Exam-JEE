import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemboursmentComponent } from './remboursment.component';

describe('RemboursmentComponent', () => {
  let component: RemboursmentComponent;
  let fixture: ComponentFixture<RemboursmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RemboursmentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemboursmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
