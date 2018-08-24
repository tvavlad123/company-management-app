import { CompMngtPage } from './app.po';

describe('comp-mngt App', () => {
  let page: CompMngtPage;

  beforeEach(() => {
    page = new CompMngtPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
